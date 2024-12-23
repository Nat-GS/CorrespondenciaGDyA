import React from 'react';
import styles from './page.module.css';

export default function Login() {
  return (
    <div className={styles.container}>
      <div className={styles.card}>
        <h1>Inicio de Sesión</h1>
        <form>
          <input type="text" placeholder="Usuario" className={styles.input} />
          <input type="password" placeholder="Contraseña" className={styles.input} />
        <p className={styles.message}>
          ¿No tienes una cuenta? <a href="/register">Regístrate</a>
        </p>
          <button type="submit" className={styles.button}>Ingresar</button>
        </form>
      </div>
    </div>
  );
}
