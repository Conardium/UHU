using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace PrácticaFinal_Ismael_Da_Palma_Fernández.paginas
{
    /// <summary>
    /// Lógica de interacción para paginaInicio.xaml
    /// </summary>
    public partial class paginaInicio : Page
    {
        private List<string> usuarios = new List<string>();
        private List<string> claves = new List<string>();

        public paginaInicio()
        {
            InitializeComponent();

            string linea;
            List<string> lista = new List<string>();
            try
            {
                StreamReader sr = new StreamReader("C:/Users/ismae/OneDrive/Escritorio/DIU/PRÁCTICAS/PrácticaFinal_Ismael_Da_Palma_Fernández/usuarios.txt");
                linea = sr.ReadLine();
                while (linea != null)
                {
                    usuarios.Add(linea); //Guardamos nombre
                    linea = sr.ReadLine();
                    claves.Add(linea); //Guardamos contraseña
                    linea = sr.ReadLine();
                }
                sr.Close();
            }
            catch(Exception ex)
            {
                MessageBox.Show("Error en el fichero: " + ex.Message, "Error");
            }
            finally {}
            DataContext = usuarios;
        }
        
        private void ElegirUsuario(object sender, SelectionChangedEventArgs e)
        {
            
        }


        private void Iniciar(object sender, RoutedEventArgs e)
        {
            if (claves[ComboBoxUsuarios.SelectedIndex].Contains(Clave.Password))
                NavigationService.Navigate(new paginaPrincipal());
            else
                MessageBox.Show("Las credenciales introducidas no son correctas", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
        }

        private void SalirAplicación(object sender, RoutedEventArgs e)
        {
            Environment.Exit(0);
        }
    }
}
