"use client";

import { useState } from "react";
import styles from './MySideBar.module.css';

interface MySideBarProps {
  onSelect: (option: string) => void;
}

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
        <li className={styles.navItem} onClick={() => handleSelect('registro')}>
          <div className={styles.iconWrapper}>
            <img src="/assets/images/list.png" alt="Registro" className={styles.icon} />
          </div>
          {isVisible && <span>Registro de correspondencia</span>}
        </li>
        <li className={styles.navItem} onClick={() => handleSelect('monitoreo')}>
          <div className={styles.iconWrapper}>
            <img src="/assets/images/monitoreo.png" alt="Monitoreo" className={styles.icon} />
          </div>
          {isVisible && <span>Monitoreo</span>}
        </li>
      </ul>
    </div>
  );
}