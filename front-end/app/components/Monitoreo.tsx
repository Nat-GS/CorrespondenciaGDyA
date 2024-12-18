import React, { useState } from "react";
import styles from "./Monitoreo.module.css";
import Image from "next/image";
import Modal from "./Modal";

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

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedUser, setSelectedUser] = useState<User | null>(null);

  const handleViewClick = (user: User) => {
    setSelectedUser(user);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedUser(null);
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Monitoreo</h1>
      <table className={styles.table}>
        <thead>
          <tr>
            <th className={styles.tableHeader}>N</th>
            <th className={styles.tableHeader}>Fecha</th>
            <th className={styles.tableHeader}>Remitente</th>
            <th className={styles.tableHeader}>Referencia</th>
            <th className={styles.tableHeader}>Detalle</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={item.id}>
              <td className={styles.tableCell}>{index + 1}</td>
              <td className={styles.tableCell}>{item.nombre}</td>
              <td className={styles.tableCell}>{item.apellido}</td>
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
                    className={styles.viewButton}
                    onClick={() => handleViewClick(item)}
                  >
                    <Image
                      src="/assets/images/view.png"
                      alt="View"
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
        <Modal user={selectedUser} onClose={closeModal} />
      )}
    </div>
  );
}
