"use client";

import React, { useState } from "react";
import styles from "./page.module.css";
import { toast } from "react-toastify";
import { BASE_URL } from "../../config/global";

export default function Register() {
  const [formData, setFormData] = useState({
    name: "",
    ci: "",
    fatherLastName: "",
    motherLastName: "",
    email: "",
    password: "",
    confirmPassword: "",
    cellPhone: "",
    description: "",
  });

  const [loading, setLoading] = useState(false);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    // Validar contraseñas
    if (formData.password !== formData.confirmPassword) {
      toast.error("Las contraseñas no coinciden");
      setLoading(false);
      return;
    }

    try {
      const response = await fetch(`${BASE_URL}/users/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: formData.name,
          ci: formData.ci,
          fatherLastName: formData.fatherLastName,
          motherLastName: formData.motherLastName,
          email: formData.email,
          password: formData.password,
          cellPhone: formData.cellPhone,
          description: formData.description,
        }),
      });

      if (response.ok) {
        const data = await response.json();
        toast.success(data.message || "¡Usuario registrado exitosamente!");
        setFormData({
          name: "",
          ci: "",
          fatherLastName: "",
          motherLastName: "",
          email: "",
          password: "",
          confirmPassword: "",
          cellPhone: "",
          description: "",
        });

        // Redirigir al inicio de sesión después de un breve retraso
        setTimeout(() => {
          window.location.href = "/login"; // Cambiar a la ruta de tu página de inicio de sesión
        }, 1500);
      } else {
        const errorData = await response.json();
        toast.error(errorData.message || "Error al registrar usuario");
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
        <h1>Registrarse</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="name"
            placeholder="Nombre"
            value={formData.name}
            onChange={handleInputChange}
            className={styles.input}
            required
          />
          <input
            type="text"
            name="fatherLastName"
            placeholder="Apellido Paterno"
            value={formData.fatherLastName}
            onChange={handleInputChange}
            className={styles.input}
            required
          />
          <input
            type="text"
            name="motherLastName"
            placeholder="Apellido Materno"
            value={formData.motherLastName}
            onChange={handleInputChange}
            className={styles.input}
            required
          />
          <input
            type="text"
            name="ci"
            placeholder="CI"
            value={formData.ci}
            onChange={handleInputChange}
            className={styles.input}
            required
          />
          <input
            type="email"
            name="email"
            placeholder="Correo Electrónico"
            value={formData.email}
            onChange={handleInputChange}
            className={styles.input}
            required
          />
          <input
            type="password"
            name="password"
            placeholder="Contraseña"
            value={formData.password}
            onChange={handleInputChange}
            className={styles.input}
            required
          />
          <input
            type="password"
            name="confirmPassword"
            placeholder="Confirmar Contraseña"
            value={formData.confirmPassword}
            onChange={handleInputChange}
            className={styles.input}
            required
          />
          <input
            type="text"
            name="cellPhone"
            placeholder="Teléfono"
            value={formData.cellPhone}
            onChange={handleInputChange}
            className={styles.input}
            required
          />
          <input
            type="text"
            name="description"
            placeholder="Descripción"
            value={formData.description}
            onChange={handleInputChange}
            className={styles.input}
            required
          />
          <button type="submit" className={styles.button} disabled={loading}>
            {loading ? "Registrando..." : "Registrarse"}
          </button>
        </form>
      </div>
    </div>
  );
}
