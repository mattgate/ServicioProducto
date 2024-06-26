USE [ProductoDB]
GO
/****** Object:  StoredProcedure [dbo].[sp_actualiza_producto_por_id]    Script Date: 07/05/2024 05:56:16 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Angel Tah>
-- Create date: <06/05/2024>
-- Description:	<Modifica un producto en BD>
-- =============================================
CREATE PROCEDURE [dbo].[sp_actualiza_producto_por_id]
	@id INT,
	@nombre NVARCHAR(100),
    @descripcion NVARCHAR(255),
    @existencia INT,
    @precio FLOAT
AS
BEGIN

	DECLARE @fecha_de_ultima_modificacion DATETIME2(6);
	DECLARE @fecha_actual DATETIME = GETDATE();
	DECLARE @horas_diferencia INT

	SELECT @fecha_de_ultima_modificacion = fecha_de_modificacion 
	  FROM producto 
	 WHERE id = @id;

	SET @horas_diferencia = ABS(DATEDIFF(HOUR, @fecha_de_ultima_modificacion, @fecha_actual));

	IF @horas_diferencia >= 12
	BEGIN
		UPDATE producto
			SET nombre      = ISNULL(@nombre, nombre),
				descripcion = ISNULL(@descripcion, descripcion),
				existencia  = @existencia,
				precio      = @precio,
				fecha_de_modificacion = SYSDATETIME()
			WHERE id = @id;
		
	END
	ELSE
	BEGIN
		RAISERROR('No se puede actualizar el dato debido a que no han pasado 12 horas desde la última modificación, apenas han pasado %d', 16, 1, @horas_diferencia);
	END

END
GO
/****** Object:  StoredProcedure [dbo].[sp_crear_producto]    Script Date: 07/05/2024 05:56:16 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Angel Tah>
-- Create date: <06/05/2024>
-- Description:	<Inserta un producto en BD>
-- =============================================
CREATE PROCEDURE [dbo].[sp_crear_producto]
    @nombre NVARCHAR(100),
    @descripcion NVARCHAR(255),
    @existencia INT,
    @precio FLOAT
AS
BEGIN
	INSERT INTO 
	producto (nombre, descripcion, existencia, precio, fecha_de_modificacion)
	VALUES   (@nombre, @descripcion, @existencia, @precio, SYSDATETIME());
END
GO
/****** Object:  StoredProcedure [dbo].[sp_elimina_producto_por_id]    Script Date: 07/05/2024 05:56:16 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Angel Tah>
-- Create date: <06/05/2024>
-- Description:	<Eliminar un producto por Id>
-- =============================================
CREATE PROCEDURE [dbo].[sp_elimina_producto_por_id]
	@id INT
AS
BEGIN
	DELETE producto
	 WHERE id = @id;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_leer_producto_por_id]    Script Date: 07/05/2024 05:56:16 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Angel Tah>
-- Create date: <06/05/2024>
-- Description:	<Buscar un producto por Id>
-- =============================================
CREATE PROCEDURE [dbo].[sp_leer_producto_por_id]
	@id INT
AS
BEGIN
	SELECT *
	  FROM producto
	 WHERE id = @id
END
GO
/****** Object:  StoredProcedure [dbo].[sp_leer_productos]    Script Date: 07/05/2024 05:56:16 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Angel Tah>
-- Create date: <06/05/2024>
-- Description:	<Busca todos los productos de la tabla producto.>
-- =============================================
CREATE PROCEDURE [dbo].[sp_leer_productos]
AS
BEGIN
	SELECT *
      FROM producto 
END
GO
