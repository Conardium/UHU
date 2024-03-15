using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;

namespace Servidor_SW
{
    class Servidor
    {
        static void Main(string[] args)
        {
            Console.WriteLine("***** Host del Servicio Sudoku de WCF *****");
            using (ServiceHost miHost = new ServiceHost(typeof(GestorJuegos_Web.Servicio)))
            {
                miHost.Open();
                Console.WriteLine("Servidor Funcionando .....");

                Console.WriteLine("Nombre: {0}", miHost.Description.ConfigurationName);
                Console.WriteLine("Puerto: {0}", miHost.BaseAddresses[0].Port);
                Console.WriteLine("Path: {0}", miHost.BaseAddresses[0].LocalPath);
                Console.WriteLine("Protocolo: {0}", miHost.BaseAddresses[0].Scheme);

                Console.WriteLine("Pulsa una tecla para cerrarlo");
                Console.ReadLine();
                miHost.Close();
            }
        }
    }
}
