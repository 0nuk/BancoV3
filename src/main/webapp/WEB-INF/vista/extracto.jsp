<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table id="tabla_extracto">
	<thead>
		<tr>
			<th>Tarjeta</th>
			<th>Fecha</th>
			<th>Tipo</th>
			<th>Proveedor</th>
			<th>Importe</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="tarjeta" items="${mapTarMov.keySet()}">
			<tr class="filas_ext">
				<td class="celda">${tarjeta.getPan()}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach var="mov" items="${mapTarMov.get(tarjeta)}">
				<tr>
					<td></td>
					<td><fmt:formatDate value="${mov.getFecha()}"
							pattern="dd/MM/YYYY" /></td>
					<td>${mov.getTipo().getTipo()}</td>
					<td>${mov.getProveedor()}</td>
					<td><fmt:formatNumber type="currency"
							value="${mov.getImporte()}" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td class="celda">Total:</td>
				<td class="celda"><fmt:formatNumber type="currency"
						value="${mapTarTot.get(tarjeta)}" /></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">Total: <fmt:formatNumber type="currency"
					value="${totalTotales}" /></td>
		</tr>
	</tfoot>
</table>
