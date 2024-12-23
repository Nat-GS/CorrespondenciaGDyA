"use client";

import React, {
  createContext,
  useContext,
  useState,
  useEffect,
  ReactNode,
  useCallback,
} from "react";
import {jwtDecode} from "jwt-decode";
import { useRouter } from "next/navigation";
import { useCookies } from "next-client-cookies";

// Definir el contexto
interface SessionContextType {
  token: string | null;
  login: (newToken: string) => void;
  logout: () => void;
  sessionExpired: boolean;
  userDetails: UserDetail | null;
}

export interface UserDetail {
  name: string;
  role: string[];
  authorities: string;
  userId: number;
}

interface DecodedToken {
  sub: string;
  role: string[];
  authorities: string;
  userId: number;
  name: string;
  exp: number;
}

// Crear contexto con valores iniciales
const SessionContext = createContext<SessionContextType | null>(null);

// Definir las props para el provider
interface SessionProviderProps {
  children: ReactNode;
}

export const SessionProvider: React.FC<SessionProviderProps> = ({
  children,
}) => {
  const cookies = useCookies(); // Obtener las cookies
  const router = useRouter();
  const [userDetails, setUserDetails] = useState<UserDetail | null>(null);
  const [sessionExpired, setSessionExpired] = useState(false);

  const [token, setToken] = useState<string | null>(() => {
    const storedToken =
      typeof window !== "undefined" ? localStorage.getItem("token") : null;
    return storedToken;
  });

  useEffect(() => {
    if (token) {
      try {
        const decoded: DecodedToken = jwtDecode(token);
        setUserDetails({
          name: decoded.name,
          role: decoded.role,
          authorities: decoded.authorities,
          userId: decoded.userId,
        });
        setSessionExpired(false);
      } catch (error) {
        console.error("Error al decodificar el token:", error);
        logout();
      }
    } else {
      setUserDetails(null);
    }
  }, [token]);

  const login = useCallback((newToken: string) => {
    localStorage.setItem("token", newToken);
    cookies.set("token", newToken);
    setToken(newToken);
    setSessionExpired(false);
  }, []);

  const logout = useCallback(() => {
    localStorage.removeItem("token");
    cookies.remove("token");
    setToken(null);
    setSessionExpired(true);
    router.push("/");
  }, [router]);

  return (
    <SessionContext.Provider
      value={{ token, userDetails, login, logout, sessionExpired }}
    >
      {children}
    </SessionContext.Provider>
  );
};

export const useSession = () => {
  const context = useContext(SessionContext);
  if (!context) {
    throw new Error("useSession debe ser usado dentro de un SessionProvider");
  }
  return context;
};
