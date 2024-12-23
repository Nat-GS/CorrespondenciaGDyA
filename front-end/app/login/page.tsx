"use client";

import React, { useState } from "react";
import styles from "./page.module.css";
import { toast } from "react-toastify";
import { useSession } from "../providers/SessionProvider";
import { BASE_URL } from "../../config/global";

export default function Login() {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const { login } = useSession();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    setLoading(true);
  
    try {
      const response = await fetch(`${BASE_URL}/users/log-in`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username,
          password,
        }),
      });
  
      if (response.ok) {
        const data = await response.json();
        login(data.jwt); // Almacena el JWT en la sesión
        toast.success("¡Inicio de sesión exitoso! Redirigiendo...");
        setTimeout(() => {
          window.location.href = "/dashboard";
        }, 1500);
      } else {
        const errorData = await response.json();
        toast.error(errorData.message || "Error al iniciar sesión");
      }
    } catch (error) {
      console.error("Error al conectar con el backend:", error);
      toast.error("No se pudo conectar con el servidor.");
    } finally {
      setLoading(false);
    }
  };
  

  return (
    <div className={styles.container}>
      <div className={styles.card}>
        <h1>Inicio de Sesión</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Usuario"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className={styles.input}
            required
          />
          <input
            type="password"
            placeholder="Contraseña"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className={styles.input}
            required
          />
          <p className={styles.message}>
            ¿No tienes una cuenta? <a href="/register">Regístrate</a>
          </p>
          <button type="submit" className={styles.button} disabled={loading}>
            {loading ? "Ingresando..." : "Ingresar"}
          </button>
        </form>
      </div>
    </div>
  );
}
