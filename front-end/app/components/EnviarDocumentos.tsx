import React, { useState } from "react";
import styles from "./EnviarDocumentos.module.css";
import Image from "next/image";

interface Destinatario {
  nombre: string;
  correo: string;
}

export default function EnviarDocumentos() {
  const [destinatarios, setDestinatarios] = useState<Destinatario[]>([]);
  const [nombre, setNombre] = useState("");
  const [correo, setCorreo] = useState("");
  const [archivos, setArchivos] = useState<File[]>([]);

  const handleAddDestinatario = () => {
    if (nombre && correo) {
      setDestinatarios([...destinatarios, { nombre, correo }]);
      setNombre("");
      setCorreo("");
    }
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setArchivos([...archivos, ...Array.from(e.target.files)]);
    }
  };

  const handleRemoveFile = (index: number) => {
    setArchivos(archivos.filter((_, i) => i !== index));
  };

  const handleSubmit = () => {
    // Logic to handle the form submission
    console.log("Archivos:", archivos);
    console.log("Destinatarios:", destinatarios);
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Enviar Documentos</h1>
      <div className={styles.fileUpload}>
        <label htmlFor="fileInput" className={styles.fileLabel}>
          Seleccionar Archivos
        </label>
        <input
          type="file"
          id="fileInput"
          className={styles.fileInput}
          onChange={handleFileChange}
          multiple
        />
        {archivos.length > 0 && (
          <div className={styles.uploadedFiles}>
            {archivos.map((archivo, index) => (
              <div key={index} className={styles.uploadedFile}>
                <Image
                  src="/assets/images/uploaded.png"
                  alt="Uploaded"
                  width={50}
                  height={50}
                />
                <div className={styles.uploadMessage}>
                  <a
                    href={URL.createObjectURL(archivo)}
                    target="_blank"
                    rel="noopener noreferrer"
                    className={styles.fileLink}
                  >
                    {archivo.name}
                  </a>
                  <button
                    onClick={() => handleRemoveFile(index)}
                    className={styles.removeButton}
                  >
                    Eliminar
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
      <div className={styles.destinatarios}>
        <h2>Añadir Destinatarios</h2>
        <input
          type="text"
          placeholder="Nombre"
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
          className={styles.input}
        />
        <input
          type="email"
          placeholder="Correo"
          value={correo}
          onChange={(e) => setCorreo(e.target.value)}
          className={styles.input}
        />
        <button onClick={handleAddDestinatario} className={styles.addButton}>
          Añadir Destinatario
        </button>
        <ul className={styles.destinatarioList}>
          {destinatarios.map((destinatario, index) => (
            <li key={index} className={styles.destinatarioItem}>
              {destinatario.nombre} - {destinatario.correo}
            </li>
          ))}
        </ul>
      </div>
      <button onClick={handleSubmit} className={styles.submitButton}>
        Enviar
      </button>
    </div>
  );
}