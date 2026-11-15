from odoo import models, fields

class PedidoRestaurante(models.Model):
    _name = 'restaurante.pedido'
    _description = 'Modelo para gestionar los pedidos del restaurante'

    ordernumber = fields.Integer(string='Número de Pedido', required=True)
    table = fields.Integer(string='Mesa', required=True)
    waiter = fields.Char(string='Camarero', required=True)
    ordertime = fields.Datetime(string='Fecha y Hora', required=True)
    price = fields.Float(string='Precio', required=True)
    productname = fields.Char(string='Producto', required=True)

    # Campo de seleccion con los estados fijos requeridos
    state = fields.Selection([
        ('registrado', 'Registrado'),
        ('preparandose', 'Preparandose'),
        ('listo', 'Listo'),
        ('servido', 'Servido')
    ], string= 'Estado', required=True, default='registrado')