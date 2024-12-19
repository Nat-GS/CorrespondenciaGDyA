import React, { useState } from "react";
import styles from "./EditModal.module.css";

interface Correspondencia {
  id: number;
  nombre: string;
  correo: string;
  documento: string;
  hasObservations: boolean;
}

interface EditModalProps {
  correspondencia: Correspondencia;
  onClose: () => void;
}

const EditModal: React.FC<EditModalProps> = ({ correspondencia, onClose }) => {
  const [fechaEntrega, setFechaEntrega] = useState("");
  const [fechaRecepcion, setFechaRecepcion] = useState("");

  const handleSave = () => {
    onClose();
  };

  return (
    <div className={styles.modalOverlay}>
      <div className={styles.modalContent}>
        <button className={styles.closeButton} onClick={onClose}>
          ⬅
        </button>
        <h2>Documento de {correspondencia.nombre}</h2>
        <p>
          <strong>Nombre:</strong> {correspondencia.nombre}
        </p>
        <p>
          <strong>Correo:</strong> {correspondencia.correo}
        </p>
            <p>
              <strong>Observaciones:</strong>{" "}
              {correspondencia.hasObservations
                ? "Sí"
                : "No existen observaciones previas"}
            </p>
        <br />
        <div className={styles.contentWrapper}>
          <div className={styles.formContainer}>
            <p>
              <strong>Comentarios/Instrucciones:</strong>
            </p>
            <textarea
              placeholder="Comentarios/ Instrucciones"
              className={styles.textarea}
            ></textarea>
            <p>
              <strong>Fecha de Entrega:</strong>
            </p>
            <input
              type="date"
              value={fechaEntrega}
              onChange={(e) => setFechaEntrega(e.target.value)}
              className={styles.dateInput}
            />
            <p>
              <strong>Fecha de Recepción:</strong>
            </p>
            <input
              type="date"
              value={fechaRecepcion}
              onChange={(e) => setFechaRecepcion(e.target.value)}
              className={styles.dateInput}
            />
          </div>
          <div className={styles.imageWrapper}>
            <img
              src="/assets/images/document.png"
              alt="Documento"
              className={styles.image}
            />
          </div>
        </div>
<div className={styles.saveButton}>
<button  onClick={handleSave}>
          Guardar la Información
        </button>
</div>
      </div>
    </div>
  );
};

export default EditModal;
