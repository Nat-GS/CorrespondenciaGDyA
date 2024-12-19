import React, { useState } from 'react';
import styles from './CorrespondenciaPorRevisar.module.css';
import Image from 'next/image';
import EditModal from './EditModal'; 

interface Correspondencia {
  id: number;
  nombre: string;
  correo: string;
  documento: string;
  hasObservations: boolean;
}

export default function CorrespondenciaPorRevisar() {
  const [data, setData] = useState<Correspondencia[]>([
    { id: 1, nombre: 'Juan Pérez', correo: 'juan.perez@example.com', documento: 'Documento 1', hasObservations: false },
    { id: 2, nombre: 'María González', correo: 'maria.gonzalez@example.com', documento: 'Documento 2', hasObservations: false },
    { id: 3, nombre: 'Carlos Rodríguez', correo: 'carlos.rodriguez@example.com', documento: 'Documento 3', hasObservations: false },
    { id: 4, nombre: 'Ana Martínez', correo: 'ana.martinez@example.com', documento: 'Documento 4', hasObservations: false },
  ]);

  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [selectedCorrespondencia, setSelectedCorrespondencia] = useState<Correspondencia | null>(null);

  const handleEditClick = (correspondencia: Correspondencia) => {
    setSelectedCorrespondencia(correspondencia);
    setIsEditModalOpen(true);
  };

  const closeEditModal = () => {
    setIsEditModalOpen(false);
    setSelectedCorrespondencia(null);
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Correspondencia por Revisar</h1>
      <table className={styles.table}>
        <thead>
          <tr>
            <th className={styles.tableHeader}>N</th>
            <th className={styles.tableHeader}>Nombre</th>
            <th className={styles.tableHeader}>Correo</th>
            <th className={styles.tableHeader}>Documento</th>
            <th className={styles.tableHeader}>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={item.id}>
              <td className={styles.tableCell}>{index + 1}</td>
              <td className={styles.tableCell}>{item.nombre}</td>
              <td className={styles.tableCell}>{item.correo}</td>
              <td className={styles.tableCell}>{item.documento}</td>
              <td className={styles.tableCell}>
                {item.hasObservations ? (
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
      {isEditModalOpen && selectedCorrespondencia && (
        <EditModal correspondencia={selectedCorrespondencia} onClose={closeEditModal} />
      )}
    </div>
  );
}