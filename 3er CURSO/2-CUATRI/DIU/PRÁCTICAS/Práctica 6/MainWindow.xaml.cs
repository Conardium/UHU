using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Práctica_6
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void ChequeadoBackground(object sender, RoutedEventArgs e)
        {
            RadioButton radio = sender as RadioButton;
            if(radio != null)
            {
                String texto = (String)radio.Content;
                if (texto == "Yellow")
                {
                    Borde.Background = Brushes.Yellow;
                    Bordecito.Background = Brushes.Yellow;
                }
                    
                if (texto == "Green")
                {
                    Borde.Background = Brushes.Green;
                    Bordecito.Background = Brushes.Green;
                }
                if (texto == "Blue")
                {
                    Borde.Background = Brushes.Blue;
                    Bordecito.Background = Brushes.Blue;
                }
                if (texto == "White")
                {
                    Borde.Background = Brushes.White;
                    Bordecito.Background = Brushes.White;
                }
            }
        }

        private void ChequeadoBorderbrush(object sender, RoutedEventArgs e)
        {
            RadioButton radio = sender as RadioButton;
            if (radio != null)
            {
                String texto = (String)radio.Content;
                if (texto == "Yellow")
                {
                    Borde.BorderBrush = Brushes.Yellow;
                    Bordecito.BorderBrush = Brushes.Yellow;
                }

                if (texto == "Green")
                {
                    Borde.BorderBrush = Brushes.Green;
                    Bordecito.BorderBrush = Brushes.Green;
                }
                if (texto == "Blue")
                {
                    Borde.BorderBrush = Brushes.Blue;
                    Bordecito.BorderBrush = Brushes.Blue;
                }
                if (texto == "Black")
                {
                    Borde.BorderBrush = Brushes.Black;
                    Bordecito.BorderBrush = Brushes.Black;
                }
            }
        }
    }
}
