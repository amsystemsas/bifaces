CREATE DATABASE  IF NOT EXISTS `cobusDB` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cobusDB`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: cobusDB
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dynamicobject`
--

DROP TABLE IF EXISTS `dynamicobject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dynamicobject` (
  `iddynamicobject` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria auto incrementable de la tabla',
  `name` varchar(200) NOT NULL COMMENT 'Nombre de la tabla creada',
  `category` smallint(3) NOT NULL COMMENT 'Categoria a la que pertenece la tabla',
  `status` smallint(1) NOT NULL DEFAULT '1' COMMENT '0: Cacelled; 1: Active;  2: Eliminate',
  PRIMARY KEY (`iddynamicobject`),
  UNIQUE KEY `do_name_UNIQUE` (`name`),
  KEY `fk_dynamicobject_dynamicobjectcategory1_idx` (`category`),
  CONSTRAINT `fk_dynamicobject_dynamicobjectcategory` FOREIGN KEY (`category`) REFERENCES `dynamicobjectcategory` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='Tabla central para registros dinamicos. Cada tupla represena la existencia de una tabla creada desde el aplicativo';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dynamicobjectcategory`
--

DROP TABLE IF EXISTS `dynamicobjectcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dynamicobjectcategory` (
  `iddocategory` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria auto incrementable de la tabla',
  `description` varchar(45) NOT NULL COMMENT 'Descripcion de la categoria',
  `type` smallint(3) NOT NULL COMMENT 'Categoria',
  PRIMARY KEY (`iddocategory`),
  UNIQUE KEY `doc_type_UNIQUE` (`type`),
  UNIQUE KEY `doc_description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Almacena las categorias posible a la que puede pertenecer una tabla diamica';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dynamicobjectproperty`
--

DROP TABLE IF EXISTS `dynamicobjectproperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dynamicobjectproperty` (
  `idproperty` int(11) NOT NULL COMMENT 'Identificador de la propiedad',
  `iddynamicobject` int(11) NOT NULL COMMENT 'Identificador de la la tabla dinamica',
  `operationdate` date DEFAULT NULL COMMENT 'Fecha de vinculacion entre una propiedad y la tabla dinamica',
  PRIMARY KEY (`idproperty`,`iddynamicobject`),
  KEY `fk_property_has_dynamicobject_dynamicobject1_idx` (`iddynamicobject`),
  KEY `fk_property_has_dynamicobject_property_idx` (`idproperty`),
  CONSTRAINT `fk_property_has_dynamicobject_dynamicobject1` FOREIGN KEY (`iddynamicobject`) REFERENCES `dynamicobject` (`iddynamicobject`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_property_has_dynamicobject_property` FOREIGN KEY (`idproperty`) REFERENCES `property` (`idproperty`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Registra la relacion entre una propiedad y una tabla dinamica';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gtemp2`
--

DROP TABLE IF EXISTS `gtemp2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gtemp2` (
  `IDDT` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador Tabla Dinamica Generada',
  `PROP2STR` varchar(1000) DEFAULT NULL,
  `PROP2VALUE` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`IDDT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gtemp3`
--

DROP TABLE IF EXISTS `gtemp3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gtemp3` (
  `IDDT` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador Tabla Dinamica Generada',
  `PROP7STR` varchar(1000) DEFAULT NULL,
  `PROP7VALUE` varchar(1000) DEFAULT NULL,
  `PROP3STR` varchar(1000) DEFAULT NULL,
  `PROP3VALUE` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`IDDT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `messagetranslation`
--

DROP TABLE IF EXISTS `messagetranslation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messagetranslation` (
  `idmtranslation` varchar(45) NOT NULL COMMENT 'Identificador del mensaje',
  `category` varchar(45) NOT NULL COMMENT 'Categoria o modulo al que pertenece',
  `locale` varchar(45) NOT NULL COMMENT 'd = Lenguaje Defecto (Espano - es);\nen = Ingles\nes = Espanol\nit   = Italiano\npt = Portugues',
  `message` varchar(45) NOT NULL COMMENT 'Mensaje que visualizara el usuario segun el locale',
  PRIMARY KEY (`idmtranslation`,`category`,`locale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `persistent_loggin`
--

DROP TABLE IF EXISTS `persistent_loggin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_loggin` (
  `SERIES` varchar(45) NOT NULL,
  `USERNAME` varchar(45) NOT NULL,
  `TOKEN` varchar(45) NOT NULL,
  `LASTUSED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SERIES`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `idproperty` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador de la Tabla',
  `name` varchar(60) NOT NULL COMMENT 'Nombre de la propiedad',
  `label` varchar(200) DEFAULT NULL,
  `propertytype` smallint(2) DEFAULT '0' COMMENT '0: String;  1: Integer; 2: Float;\n',
  `renderingtype` smallint(3) DEFAULT '0' COMMENT '0: InputText; 1: TextArea; 2: ComboList; \n3: Radio; 4: CheckBox; 5: Date;  6: Label;\n7: Tab;',
  `expresionvalidator` varchar(200) DEFAULT NULL COMMENT 'Destinado para expresiones regulares',
  `formula` varchar(200) DEFAULT NULL COMMENT 'Almacenamiento de Formulas',
  `visible` smallint(1) DEFAULT '1',
  `editable` smallint(1) DEFAULT '1',
  `parentproperty` varchar(60) DEFAULT NULL,
  `defaultvalue` varchar(45) DEFAULT NULL,
  `required` smallint(1) DEFAULT '0',
  `mask` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idproperty`),
  UNIQUE KEY `symbol_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `propertyoptionlabel`
--

DROP TABLE IF EXISTS `propertyoptionlabel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propertyoptionlabel` (
  `idpolabel` int(11) NOT NULL COMMENT 'Identifiacdor de la tabla',
  `value` varchar(80) DEFAULT NULL COMMENT 'Etiqueta/Mensaje/Valor',
  `locale` varchar(3) NOT NULL COMMENT 'Español: es; Ingles: en;',
  PRIMARY KEY (`idpolabel`,`locale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `propertyoptionlist`
--

DROP TABLE IF EXISTS `propertyoptionlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propertyoptionlist` (
  `idpolist` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador de la tabla',
  `idproperty` int(11) NOT NULL COMMENT 'Identificador de la propiedad a la que pertenece la opcion',
  `description` varchar(45) NOT NULL COMMENT 'Descripcion',
  `value` float NOT NULL COMMENT 'Valor de la opcion',
  PRIMARY KEY (`idpolist`),
  KEY `fk_polist_property_idx` (`idproperty`),
  CONSTRAINT `fk_polist_property` FOREIGN KEY (`idproperty`) REFERENCES `property` (`idproperty`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `idrole` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador del rol',
  `type` varchar(45) NOT NULL COMMENT 'Tipo del Rol',
  PRIMARY KEY (`idrole`),
  UNIQUE KEY `DESCRIPTION_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_i18n_vertical`
--

DROP TABLE IF EXISTS `t_i18n_vertical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_i18n_vertical` (
  `CODE` varchar(100) NOT NULL,
  `LOCALE` varchar(100) NOT NULL,
  `MSG` varchar(255) NOT NULL,
  PRIMARY KEY (`CODE`,`LOCALE`),
  UNIQUE KEY `U_T_I18N_VERTICAL` (`CODE`,`LOCALE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador del Usuario',
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `initdate` date DEFAULT NULL COMMENT 'Fecha inicial de vigncia',
  `enddate` date DEFAULT NULL COMMENT 'Fecha de final de la vigencia',
  `expirable` smallint(1) NOT NULL COMMENT 'Indica si la cuenta es expirable',
  `status` smallint(1) NOT NULL COMMENT 'Activo: 1; Inactivo: 2;',
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `USERNAME_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Informacion referente a los usuarios del sistema cobu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `iduser` int(11) NOT NULL COMMENT 'Identificador del usuario',
  `idrole` int(11) NOT NULL COMMENT 'Identificador del rol',
  PRIMARY KEY (`iduser`,`idrole`),
  KEY `fk_User_has_ROLE_ROLE1_idx` (`idrole`),
  KEY `fk_User_has_ROLE_User1_idx` (`iduser`),
  CONSTRAINT `fk_user_role_rid` FOREIGN KEY (`idrole`) REFERENCES `role` (`idrole`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_uid` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Almacena la relacion de los roles que puede tener un usuario';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-02 22:04:03
