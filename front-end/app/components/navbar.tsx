"use client";
import Link from "next/link";
import Image from "next/image";
import { useState } from "react";
import styles from "./Navbar.module.css";

export default function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);
  const userRole = "Secretaria";

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
        {isLoggedIn ? (
          <>
            <li className={styles.navItem}>
              <button className={styles.btnNotification}>
                <Image
                  src="/assets/images/notification.png"
                  alt="Notification"
                  width={30}
                  height={30}
                  className={styles.profileImage}
                />{" "}
              </button>
            </li>
            <li className={styles.navItem}>
              <Link href="/perfil">
                <button className={styles.btnLogin}>
                  <Image
                    src="/assets/images/perfil.png"
                    alt="Profile"
                    width={30}
                    height={30}
                    className={styles.profileImage}
                  />
                  {userRole}
                </button>
              </Link>
            </li>
          </>
        ) : (
          <li className={styles.navItem}>
            <Link href="/login">
              <button className={styles.btnLogin}>Login</button>
            </Link>
          </li>
        )}
      </ul>
    </nav>
  );
}
