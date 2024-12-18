import React, { useState } from "react";
import styles from "./RegistroCorrespondencia.module.css";
import Image from "next/image";

interface User {
  id: number;
  nombre: string;
  apellido: string;
  correo: string;
  documento: string;
  hasObservations: boolean;
}

export default function RegistroCorrespondencia() {
  const [data, setData] = useState<User[]>([
    {
      id: 1,
      nombre: "Juan",
      apellido: "Pérez",
      correo: "juan.perez@example.com",
      documento: "Documento 1",
      hasObservations: false,
    },
    {
      id: 2,
      nombre: "María",
      apellido: "González",
      correo: "maria.gonzalez@example.com",
      documento: "Documento 2",
      hasObservations: false,
    },
    {
      id: 3,
      nombre: "Carlos",
      apellido: "Rodríguez",
      correo: "carlos.rodriguez@example.com",
      documento: "Documento 3",
      hasObservations: false,
    },
    {
      id: 4,
      nombre: "Ana",
      apellido: "Martínez",
      correo: "ana.martinez@example.com",
      documento: "Documento 4",
      hasObservations: false,
    },
  ]);

  const [selectedUser, setSelectedUser] = useState<User | null>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isConfirmationModalOpen, setIsConfirmationModalOpen] = useState(false);
  const [isObservationsModalOpen, setIsObservationsModalOpen] = useState(false);
  const [observations, setObservations] = useState("");

  const handleEditClick = (user: User) => {
    setSelectedUser(user);
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setSelectedUser(null);
  };

  const handleSave = () => {
    if (selectedUser?.hasObservations) {
      setIsObservationsModalOpen(true);
    } else {
      setIsConfirmationModalOpen(true);
    }
    setIsModalOpen(false);
  };

  const handleObservationsSubmit = () => {
    if (selectedUser) {
      setData(
        data.map((user) =>
          user.id === selectedUser.id
            ? { ...user, hasObservations: false }
            : user
        )
      );
    }
    setIsObservationsModalOpen(false);
    setIsConfirmationModalOpen(true);
  };

  const handleConfirmationAccept = () => {
    setIsConfirmationModalOpen(false);
    if (selectedUser) {
      setData(
        data.map((user) =>
          user.id === selectedUser.id
            ? { ...user, documento: "Documento revisado" }
            : user
        )
      );
    }
    setSelectedUser(null);
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Registro de correspondencia</h1>
      <table className={styles.table}>
        <thead>
          <tr>
            <th className={styles.tableHeader}>N</th>
            <th className={styles.tableHeader}>Nombre</th>
            <th className={styles.tableHeader}>Apellido</th>
            <th className={styles.tableHeader}>Correo</th>
            <th className={styles.tableHeader}>Documento</th>
            <th className={styles.tableHeader}>Recibido</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={item.id}>
              <td className={styles.tableCell}>{index + 1}</td>
              <td className={styles.tableCell}>{item.nombre}</td>
              <td className={styles.tableCell}>{item.apellido}</td>
              <td className={styles.tableCell}>{item.correo}</td>
              <td className={styles.tableCell}>{item.documento}</td>
              <td className={styles.tableCell}>
                {item.documento === "Documento revisado" ? (
                  <Image
                    className={styles.check}
                    src="/assets/images/check.png"
                    alt="Checked"
                    width={30}
                    height={30}
                  />
                ) : (
                  <button
                    className={styles.editButton}
                    onClick={() => handleEditClick(item)}
                  >
                    <Image
                      src="/assets/images/edit.png"
                      alt="Edit"
                      width={20}
                      height={20}
                    />
                  </button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {isModalOpen && selectedUser && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h2>Documento de {selectedUser.nombre}</h2>
            <label>
              Título del documento:
              <input type="text" className={styles.input} required/>
            </label>
            <label>
              Tipo de documento:
              <select className={styles.select}>
                <option value="tipo1">Tipo 1</option>
                <option value="tipo2">Tipo 2</option>
                <option value="tipo3">Tipo 3</option>
              </select>
            </label>
            <label>
              <input
                type="checkbox"
                className={styles.checkbox}
                checked={!selectedUser.hasObservations}
                onChange={(e) =>
                  setSelectedUser({
                    ...selectedUser,
                    hasObservations: !e.target.checked,
                  })
                }
              />
              Documento sin observaciones
            </label>
            <br />
            <button className={styles.closeButton} onClick={handleSave}>
              Guardar
            </button>
          </div>
        </div>
      )}

      {isObservationsModalOpen && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h2>Observaciones del documento</h2>
            <label>
              Observaciones:
              <br />
              <textarea
                className={styles.textarea}
                value={observations}
                onChange={(e) => setObservations(e.target.value)}
                required
              />
            </label>
            <br />
            <button
              className={styles.closeButton}
              onClick={handleObservationsSubmit}
            >
              Enviar Observaciones
            </button>
          </div>
        </div>
      )}

      {isConfirmationModalOpen && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h2>Confirmación</h2>
            <p>
              Al guardar el documento se enviará al primer destinatario, y se
              asignará un número de documento según orden de revisión.
            </p>
            <button
              className={styles.closeButton}
              onClick={handleConfirmationAccept}
            >
              Aceptar
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
