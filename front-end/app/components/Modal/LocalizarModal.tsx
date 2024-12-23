import React from "react";
import styles from "./LocalizarModal.module.css";
import Image from "next/image";

interface Destinatario {
  nombre: string;
  fechaRecepcion: string;
  horaRecepcion: string;
  proveido: string;
}

interface LocalizarModalProps {
  destinatario: Destinatario;
  onClose: () => void;
}

const fases = [
  { nombre: "Fase 1", estado: "true", imagen: "/assets/images/fase1.png" },
  { nombre: "Fase 2", estado: "false", imagen: "/assets/images/fase2.png" },
  { nombre: "Fase 3", estado: "pending", imagen: "/assets/images/fase3.png" },
];

const LocalizarModal: React.FC<LocalizarModalProps> = ({ destinatario, onClose }) => {
  return (
    <div className={styles.modalOverlay}>
      <div className={styles.modalContent}>
        <button className={styles.closeButton} onClick={onClose}>
          ⬅
        </button>
        <h2>Documento de {destinatario.nombre}</h2>
        <p><strong>Fecha de Entrega:</strong> {destinatario.fechaRecepcion}</p>
        <p><strong>Hora de Entrega:</strong> {destinatario.horaRecepcion}</p>
        <p><strong>Tipo de Documento:</strong> {destinatario.proveido}</p>
        <p><strong>Observaciones:</strong> En proceso...</p>
        <div className={styles.graphic}>
          {fases.map((fase, index) => (
            <div key={index} className={styles.fase}>
              <Image src={fase.imagen} alt={fase.nombre} width={50} height={50} />
              <p>{fase.nombre}</p>
              {fase.estado === "true" ? (
                <Image src="/assets/images/check.png" alt="Check" width={40} height={40} className={styles.check} />
              ) : fase.estado === "false" ? (
                <Image src="/assets/images/error.png" alt="Error" width={40} height={40} className={styles.error} />
              ) : (
                <Image src="/assets/images/pending.png" alt="Pending" width={40} height={40} className={styles.pending} />
              )}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default LocalizarModal;