"use client";

import Link from "next/link";
import Image from "next/image";
import { useState, useEffect } from "react";
import {
  Dropdown,
  DropdownTrigger,
  DropdownMenu,
  DropdownItem,
} from "@nextui-org/dropdown";
import { Button } from "@nextui-org/button";
import styles from "./Navbar.module.css";
import { useSession } from "../providers/SessionProvider";

export default function Navbar() {
  const { token, userDetails, logout } = useSession();
  const isLoggedIn = !!token; // Determina si el usuario está autenticado
  const [isClient, setIsClient] = useState(false); // Renderizado solo en cliente

  useEffect(() => {
    setIsClient(true);
  }, []);

  return (
    <nav className={styles.navbar}>
      <div className="navbar-brand">
        <Link href="/">
          <Image
            src="/assets/images/logo.png"
            alt="Logo"
            width={60}
            height={60}
          />
        </Link>
      </div>
      <ul className={styles.navbarNav}>
        {isClient && (
          <li className={styles.navItem}>
            {isLoggedIn ? (
              <Dropdown>
                <DropdownTrigger>
                  <Button className={styles.btnLogin}>
                    <Image
                      src="/assets/images/perfil.png"
                      alt="Profile"
                      width={30}
                      height={30}
                      className={styles.profileImage}
                    />
                    {userDetails?.name || "Usuario"}
                  </Button>
                </DropdownTrigger>
                <DropdownMenu className={styles.dropdownMenu}>
                  <DropdownItem key="profile">
                    <strong>Nombre:</strong> {userDetails?.name}
                  </DropdownItem>
                  <DropdownItem key="rol">
                    <strong>Rol:</strong> {userDetails?.role || "No disponible"}
                  </DropdownItem>
                  <DropdownItem key="logout" className={styles.logoutItem}>
                    <button
                      onClick={() => {
                        logout();
                        window.location.href = "/login"; // Redirige al login
                      }}
                      className={styles.logoutButton}
                    >
                      Logout
                    </button>
                  </DropdownItem>
                </DropdownMenu>
              </Dropdown>
            ) : (
              <Link href="/login">
                <Button className={styles.btnLogin}>Login</Button>
              </Link>
            )}
          </li>
        )}
      </ul>
    </nav>
  );
}
