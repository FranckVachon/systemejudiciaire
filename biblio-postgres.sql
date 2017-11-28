------------------------------------------
-- Exemple de la bibliotheque
-- Marc Frappier, Universite de Sherbrooke
-- 2000-01-25
------------------------------------------

DROP TABLE membre CASCADE;
CREATE TABLE membre (
idMembre        numeric(3) check(idMembre > 0),
nom             varchar(10) NOT NULL,
telephone       numeric(10),
limitePret      numeric(2) check(limitePret > 0 and limitePret <= 10) ,
nbpret          numeric(2) default 0 check(nbpret >= 0) ,
PRIMARY KEY (idMembre),
check(nbpret <= limitePret));

DROP TABLE livre CASCADE;
CREATE TABLE livre (
idLivre         numeric(3) check(idLivre > 0) ,
titre           varchar(10) NOT NULL,
auteur          varchar(10) NOT NULL,
dateAcquisition date not null,
idMembre        numeric(3) ,
datePret        date ,
PRIMARY KEY (idLivre),
FOREIGN KEY (idMembre) REFERENCES membre
);

DROP TABLE reservation CASCADE;
CREATE TABLE reservation (
idReservation   numeric(3) ,
idMembre        numeric(3) ,
idLivre         numeric(3) ,
dateReservation date ,
PRIMARY KEY (idReservation) ,
UNIQUE (idMembre,idLivre) ,
FOREIGN KEY (idMembre) REFERENCES membre,
FOREIGN KEY (idLivre) REFERENCES livre
);
