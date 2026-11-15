{
    'name': 'Restaurante',                  # Nombre del módulo en Odoo
    'version': '1.0',                       # Versión
    'category': 'Uncategorized',            # Categoría
    'summary': 'Gestión de pedidos de restaurante', # Resumen corto
    'description': 'Módulo para el examen de SGE.', # Descripción detallada
    'author': 'Tu Nombre y Apellidos',      # ¡Tu nombre como desarrollador!
    'depends': ['base'],                    # Módulos de los que depende (base siempre)
    'data': [
        'security/ir.model.access.csv',
        'views/pedido_view.xml',
    ],                            
    'installable': True,
    'application': True,
}