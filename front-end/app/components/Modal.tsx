import React from "react";
import styles from "./Modal.module.css";
import Link from "next/link";
import Image from "next/image";

interface User {
  id: number;
  nombre: string;
  apellido: string;
  correo: string;
  documento: string;
  hasObservations: boolean;
}

interface Destinatario {
  nombre: string;
  fechaRecepcion: string;
  horaRecepcion: string;
  proveido: string;
}

interface ModalProps {
  user: User;
  onClose: () => void;
}

const Modal: React.FC<ModalProps> = ({ user, onClose }) => {
  const destinatarios: Destinatario[] = [
    {
      nombre: "Destinatario 1",
      fechaRecepcion: "2023-10-01",
      horaRecepcion: "10:00 AM",
      proveido: "Proveido 1",
    },
    {
      nombre: "Destinatario 2",
      fechaRecepcion: "2023-10-02",
      horaRecepcion: "11:00 AM",
      proveido: "Proveido 2",
    },
    {
      nombre: "Destinatario 3",
      fechaRecepcion: "2023-10-03",
      horaRecepcion: "12:00 PM",
      proveido: "Proveido 3",
    },
  ];

  return (
    <div className={styles.modalOverlay}>
      <div className={styles.modalContent}>
        <button className={styles.closeButton} onClick={onClose}>
          ⬅
        </button>
        <h2>Documento de {user.nombre}</h2>
        <p>
          <strong>Nombre:</strong> {user.nombre} {user.apellido}
        </p>
        <p>
          <strong>Correo:</strong> {user.correo}
        </p>
        <p>
          <strong>Observaciones:</strong> {user.hasObservations}{" "}
        </p>
        <br />
        <button className={styles.downloadButton}>Descargar Documento</button>
        <div className={styles.destinatarios}>
          {destinatarios.map((destinatario, index) => (
            <div key={index} className={styles.card}>
              <h2>Destinatario {index + 1}</h2>
              <div className={styles.destinatarioName}>
                <Link href="/perfil">
                  <Image
                    src="/assets/images/perfil.png"
                    alt="Profile"
                    width={30}
                    height={30}
                    className={styles.profileImage}
                  />
                </Link>
                <p>{destinatario.nombre}</p>
              </div>
              <p>Fecha de Recepción: {destinatario.fechaRecepcion}</p>
              <p>Hora de Recepción: {destinatario.horaRecepcion}</p>
              <p>Proveído: {destinatario.proveido}</p>
            </div>
          ))}
        </div>
        <div className={styles.routeButton}>
          <button>Hoja de Ruta</button>
        </div>
      </div>
    </div>
  );
};

export default Modal;
