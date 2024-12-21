import React from 'react';
import styles from './page.module.css';

export default function Register() {
  return (
    <div className={styles.container}>
      <div className={styles.card}>
        <h1>Registrarse</h1>
        <form>
          <input type="text" placeholder="Nombre" className={styles.input} />
          <input type="text" placeholder="Apellido" className={styles.input} />
          <input type="email" placeholder="Gmail" className={styles.input} />
          <input type="password" placeholder="Contraseña" className={styles.input} />
          <input type="password" placeholder="Confirmar Contraseña" className={styles.input} />
          <select className={styles.input}>
            <option value="">Categoría de Usuario</option>
            <option value="secretaria">Secretaria</option>
            <option value="administrador">Administrador</option>
            <option value="otro">Otro</option>
          </select>
          <button type="submit" className={styles.button}>Registrarse</button>
        </form>
      </div>
    </div>
  );
}