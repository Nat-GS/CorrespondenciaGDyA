import React from "react";
import styles from "./MiCorrespondencia.module.css";
import Link from "next/link";
import Image from "next/image";

interface Destinatario {
  nombre: string;
  fechaRecepcion: string;
  horaRecepcion: string;
  proveido: string;
}

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

export default function MiCorrespondencia() {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Mi Correspondencia</h1>
      <div className={styles.destinatarios}>
        {destinatarios.map((destinatario, index) => (
          <div key={index} className={styles.card}>
            <h2>Documento {index + 1}</h2>
            <div className={styles.destinatarioName}>
              <button>Localizar</button>
            </div>
            <p>Fecha de Recepción: {destinatario.fechaRecepcion}</p>
            <p>Hora de Recepción: {destinatario.horaRecepcion}</p>
            <p>Proveído: {destinatario.proveido}</p>
          </div>
        ))}
      </div>
    </div>
  );
}