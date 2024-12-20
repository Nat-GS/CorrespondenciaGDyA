"use client";

import { useState } from "react";
import styles from './MySideBar.module.css';

interface MySideBarProps {
  onSelect: (option: string) => void;
}

interface NavItem {
  option: string;
  iconSrc: string;
  label: string;
}

const navItems: NavItem[] = [
  { option: 'registro', iconSrc: '/assets/images/list.png', label: 'Registro de correspondencia' },
  { option: 'monitoreo', iconSrc: '/assets/images/monitoreo.png', label: 'Monitoreo' },
  { option: 'correspondencia', iconSrc: '/assets/images/info.png', label: 'Correspondencia Por Revisar' },
  { option: 'misEnvios', iconSrc: '/assets/images/miCorrespondencia.png', label: 'Mi Correspondencia' },
  { option: 'enviarDocumento', iconSrc: '/assets/images/enviarDocumento.png', label: 'Enviar Documento ' },
];

export default function MySideBar({ onSelect }: MySideBarProps) {
  const [isVisible, setIsVisible] = useState(true);

  const handleSelect = (option: string) => {
    onSelect(option);
  };

  return (
    <div className={`${styles.sidebar} ${isVisible ? styles.visible : styles.hidden}`}>
      <button className={styles.toggleButton} onClick={() => setIsVisible(!isVisible)}>
        {isVisible ? '☰' : '☰'}
      </button>
      <ul className={styles.navList}>
        {navItems.map((item) => (
          <li key={item.option} className={styles.navItem} onClick={() => handleSelect(item.option)}>
            <div className={styles.iconWrapper}>
              <img src={item.iconSrc} alt={item.label} className={styles.icon} />
            </div>
            {isVisible && <span>{item.label}</span>}
          </li>
        ))}
      </ul>
    </div>
  );
}