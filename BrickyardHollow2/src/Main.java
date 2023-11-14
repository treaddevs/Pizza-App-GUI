package src;

import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.text.DecimalFormat;

public class Main extends JFrame {
    private JButton menuButton;
    private JButton buildPizzaButton;
    private final MenuPrices menu;
    private final Menu menu2;
    private final JTextField nameTextField;

    // Since Main extends JFrame, this is where the program begins
    // JFrame is like the GUI foundation to the house, where other visual components
    // including JPanels (separate containers), JRadioButtons, JLists, JLabels
    // and much more are built from
    public Main() {
        // The starting JFrame attributes are set
        setTitle("BRICKYARD HOLLOW: Pizza Ordering System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setSize(850, 540);
        setLocationRelativeTo(null);
        setVisible(true);

        // Initializes menu with prices from MenuPrices Class
        menu = new MenuPrices();
        
        // Initializes menu2 (menu without prices) from Menu Class
        menu2 = new Menu();

        // Creates an ImageIcon from the image logo file
        ImageIcon logoIcon = new ImageIcon("BH.png");

        // Creates a JLabel to display the image
        JLabel logoLabel = new JLabel(logoIcon);

        //–––––––CUSTOMER NAME PANEL–––––––//
        // Creates a panel for the customer name
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());
        namePanel.setBackground(Color.BLACK); // Set background color to black

        // Add a label for the customer name
        JLabel nameLabel = new JLabel("\nEnter customer name:");
        nameLabel.setForeground(Color.WHITE);
        namePanel.add(nameLabel, BorderLayout.NORTH);

        // Centers the label horizontally within the panel
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creates a panel to hold the nameLabel and nameTextField
        JPanel labelAndTextFieldPanel = new JPanel();
        labelAndTextFieldPanel.setLayout(new BoxLayout(labelAndTextFieldPanel, BoxLayout.Y_AXIS));
        labelAndTextFieldPanel.setBackground(Color.BLACK);

        // Adds the label and set its alignment to center
        labelAndTextFieldPanel.add(nameLabel);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creates the text field for entering the customer's name
        nameTextField = new JTextField(20);
        nameTextField.setMaximumSize(new Dimension(250, nameTextField.getPreferredSize().height)); // Set maximum width
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text field

        // Adds the text field
        labelAndTextFieldPanel.add(nameTextField);

        // Centers the labelAndTextFieldPanel horizontally within the namePanel
        labelAndTextFieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adds the labelAndTextFieldPanel to the namePanel
        namePanel.add(labelAndTextFieldPanel);

        //––––––––-BUTTONS PANEL-––––––––//
        // Creates a JPanel to hold buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout()); // Use FlowLayout for buttons side by side
        buttonsPanel.setBackground(Color.BLACK); // Set background color to black

        // Sets preferred size for buttons
        Dimension buttonSize = new Dimension(300, 200); // Adjust dimensions as needed
        menuButton.setPreferredSize(buttonSize);
        buildPizzaButton.setPreferredSize(buttonSize);
        buttonsPanel.add(menuButton);
        buttonsPanel.add(buildPizzaButton);

        //––––––––––START PANEL––––––––––//
        // Creates starting JPanel for first screen
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
        startPanel.setBackground(Color.BLACK); // Set background color to black
        startPanel.add(logoLabel, BorderLayout.NORTH); // Place image at the top
        startPanel.add(namePanel, BorderLayout.CENTER); // Place customer name text area in center
        startPanel.add(buttonsPanel, BorderLayout.SOUTH); // Place buttons at the bottom

        // Sets the content pane of the frame
        setContentPane(startPanel);
    }
    private void initComponents() {
        menuButton = new JButton("Order from Menu");
        buildPizzaButton = new JButton("Build-Your-Own Pizza");

        // ActionListeners are used for interaction with GUI components
        // This works for buttons, drop-downs, scroll bars, and more
        // If clicked, this action listener displays the pizza menu
        menuButton.addActionListener(e -> {
            // Retrieves the customer's name from the text field
            String customerName = nameTextField.getText();

            if (!customerName.isEmpty()) {

            // Creates a list box to display the menu items
            JList<String> menuList = new JList<>(menu.displayMenuPrices);
            menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            menuList.setBackground(Color.BLACK);
            menuList.setForeground(Color.WHITE);

            // Adds a scroll pane to the list box
            JScrollPane scrollPane = new JScrollPane(menuList);

            // Adds pizza slice (PizzaIcon) image
            ImageIcon pizzaIcon = new ImageIcon("PizzaIcon.png");

            // Creates a JLabel to display the image
            JLabel iconLabel = new JLabel(pizzaIcon);

            // Creates a panel for additional options
            JPanel sizesPanel = new JPanel();
            sizesPanel.setLayout(new BoxLayout(sizesPanel, BoxLayout.Y_AXIS));
            sizesPanel.setBackground(Color.BLACK);

            // Label for pizza sizes
            JLabel selectSize = new JLabel("\nSelect size");
            Font firstFont = selectSize.getFont().deriveFont(Font.PLAIN, 16); // Adjust the size as needed
            selectSize.setForeground(Color.WHITE); // Set text color
            selectSize.setFont(firstFont); // Set the font
            sizesPanel.add(selectSize);

            // Creates radio buttons for pizza size
            JRadioButton smallSizeButton = new JRadioButton("Small (12\")");
            JRadioButton largeSizeButton = new JRadioButton("Large (16\")");

            // This ButtonGroup is used for the pizza size buttons
            // Button groups in Java Swing are mutually-exclusive
            // Which means only one button to be selected at a time
            ButtonGroup sizeButtonGroup = new ButtonGroup();
            sizeButtonGroup.add(smallSizeButton);
            sizeButtonGroup.add(largeSizeButton);
            sizesPanel.add(smallSizeButton);
            sizesPanel.add(largeSizeButton);

            // Sets button display color
            smallSizeButton.setBackground(Color.BLACK);
            smallSizeButton.setForeground(Color.WHITE);
            largeSizeButton.setBackground(Color.BLACK);
            largeSizeButton.setForeground(Color.WHITE);

            // Creates a menu panel for menu list and options
            JPanel menuPanel = new JPanel(new BorderLayout());
            menuPanel.setBackground(Color.BLACK);
            menuPanel.setPreferredSize(new Dimension(805,400));
            menuPanel.add(iconLabel, BorderLayout.NORTH);
            menuPanel.add(scrollPane, BorderLayout.CENTER);
            menuPanel.add(sizesPanel, BorderLayout.EAST);

            // Displays the menu panel in a dialog
            int choice = JOptionPane.showConfirmDialog(
                    Main.this,
                    menuPanel,
                    "Select a Menu Item",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE

            );

            // Pizza prices arrays based on sizes (in order of pizza items on menu)
            double[] smallPrices = {15.75, 15.75, 13.25, 14.50, 15.50, 15.50, 15.50, 15.50, 15.75, 15.50, 15.50, 15.75, 14.75, 15.75, 15.75, 15.75, 15.75, 15.50, 15.50, 15.75, 15.50, 14.74};
            double[] largePrices = {25.00, 25.00, 18.75, 21.00, 24.50, 24.50, 24.50, 24.50, 25.00, 24.50, 24.50, 25.00, 23.50, 25.00, 25.00, 25.00, 25.00, 24.50, 24.50, 25.00, 24.50, 23.50};

            // After the user chooses a pizza from the menu, the system echos back their order
            // and provides price information to be processed based on their selection
                if (choice == JOptionPane.OK_OPTION) {
                    // Determines the pizza price based on user's selection
                    double totalPrice = 0.00;
                    int selectedIndex = menuList.getSelectedIndex();

                    if (selectedIndex >= 0 && selectedIndex < smallPrices.length) {
                        if (smallSizeButton.isSelected()) {
                            totalPrice = smallPrices[selectedIndex];
                        } else if (largeSizeButton.isSelected()) {
                            totalPrice = largePrices[selectedIndex];
                        }

                        // Ensures the trailing zeros are included in the price
                        DecimalFormat decimalFormat = new DecimalFormat("$0.00");
                        String formattedPrice = decimalFormat.format(totalPrice);

                        // Indicates selected pizza (same index position as menu with prices)
                        String selectedPizza = menu2.displayMenu[selectedIndex];

                        // Sets panel where price info is displayed
                        JPanel pricePanel = new JPanel();
                        pricePanel.setBackground(Color.BLACK);
                        pricePanel.setForeground(Color.WHITE);
                        pricePanel.setLayout(new GridLayout(5, 2)); // Two rows, one for each message

                        // Pizza prep message
                        JLabel prepLabel = new JLabel(customerName + ",\nyour pizza is being prepared!");
                        Font secondFont = prepLabel.getFont().deriveFont(Font.PLAIN, 26); // Adjust the size as needed
                        prepLabel.setForeground(Color.WHITE); // Set text color
                        prepLabel.setFont(secondFont); // Set the font
                        pricePanel.add(prepLabel);

                        // Order info message
                        JLabel infoLabel = new JLabel("\nPizza order information:");
                        Font thirdFont = infoLabel.getFont().deriveFont(Font.PLAIN, 20); // Adjust the size as needed
                        infoLabel.setForeground(Color.WHITE); // Set text color
                        infoLabel.setFont(thirdFont); // Set the font
                        pricePanel.add(infoLabel);

                        // Indicates selected size
                        JLabel sizeLabel = new JLabel("\nSelected Size:\n" + (smallSizeButton.isSelected() ? "Small" : "Large"));
                        Font fifthFont = sizeLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                        sizeLabel.setForeground(Color.WHITE); // Set text color
                        sizeLabel.setFont(fifthFont); // Set the font
                        pricePanel.add(sizeLabel);

                        // Indicates selected pizza
                        JLabel selectedLabel = new JLabel("\nSelected Pizza:\n" + selectedPizza);
                        Font fourthFont = infoLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                        selectedLabel.setForeground(Color.WHITE); // Set text color
                        selectedLabel.setFont(fourthFont); // Set the font
                        pricePanel.add(selectedLabel);

                        // Indicates the pizza price
                        JLabel priceLabel = new JLabel("\nTotal Price:\n" + formattedPrice);
                        Font sixthFont = priceLabel.getFont().deriveFont(Font.PLAIN, 24); // Adjust the size as needed
                        priceLabel.setForeground(Color.WHITE); // Set text color
                        priceLabel.setFont(sixthFont); // Set the font
                        pricePanel.add(priceLabel);

                        // Creates the order confirmation frame
                        JFrame confirmationFrame = new JFrame("Order Confirmation");
                        confirmationFrame.setPreferredSize(new Dimension(700, 360));
                        confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        confirmationFrame.getContentPane().add(pricePanel);
                        confirmationFrame.pack();
                        confirmationFrame.setLocationRelativeTo(null);
                        confirmationFrame.setVisible(true);
                    } else {
                        // Handles invalid selection
                        JOptionPane.showMessageDialog(
                                Main.this,
                                "Oops, no menu items were selected. Return to homepage to reorder!",
                                "Invalid Selection",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });

        // If clicked, this action listener displays BYO pizza options
        buildPizzaButton.addActionListener(e -> {

            // Retrieves the customer's name from the text field
            String customerName = nameTextField.getText();

            if (!customerName.isEmpty()) {

                // BYO Pizza icon
                ImageIcon byoIcon = new ImageIcon("BYO.png");

                // Label to hold BYO Pizza icon
                JLabel byoLabel = new JLabel(byoIcon);

                // Creates a panel for the BYO Pizza icon and label
                JPanel byoHeaderPanel = new JPanel(new GridBagLayout());
                byoHeaderPanel.setBackground(Color.BLACK);
                byoHeaderPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for centering

                // Creates a GridBagConstraints instance to center the content
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.CENTER;

                // Sets the size of the header panel to match the image's size
                byoHeaderPanel.setPreferredSize(new Dimension(500, 140));

                // Adds the BYO Pizza icon and label to the header panel
                byoHeaderPanel.add(byoLabel, gbc);

                //–––––––-BYO COMPONENTS PANEL-–––––––//
                // Creates a panel for the BYO Pizza components (crust, cheese, sauce, toppings)
                JPanel byoComponentsPanel = new JPanel(new GridLayout(2, 2));
                byoComponentsPanel.setBackground(Color.BLACK);

                //––––––––––CRUST PANEL––––––––––//
                // Creates a panel for crust options
                JPanel crustPanel = new JPanel(new GridBagLayout());
                crustPanel.setLayout(new BoxLayout(crustPanel, BoxLayout.Y_AXIS)); // Arrange components vertically
                crustPanel.setBackground(Color.BLACK); // Set background color

                // JLabel for the crust size
                JLabel crustLabel = new JLabel("\nChoose Crust:");
                Font secondFont = crustLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                crustLabel.setForeground(Color.WHITE); // Set text color
                crustLabel.setFont(secondFont); // Set the font

                // Creates check boxes for crust options
                JRadioButton smallButton = new JRadioButton("Small (12\")");
                JRadioButton gfButton = new JRadioButton("Gluten-free (12\")");
                JRadioButton largeButton = new JRadioButton("Large (16\")");
                smallButton.setForeground(Color.WHITE);
                largeButton.setForeground(Color.WHITE);
                gfButton.setForeground(Color.WHITE);

                // Button group for crust options
                ButtonGroup crustButtonGroup = new ButtonGroup();
                crustButtonGroup.add(smallButton);
                crustButtonGroup.add(gfButton);
                crustButtonGroup.add(largeButton);

                // Adds the crust label and check boxes to the crust panel
                crustPanel.add(crustLabel);
                crustPanel.add(smallButton);
                crustPanel.add(gfButton);
                crustPanel.add(largeButton);

                //––––––––––CHEESE PANEL––––––––––//
                // Creates a panel for cheese options
                JPanel cheesePanel = new JPanel();
                cheesePanel.setLayout(new BoxLayout(cheesePanel, BoxLayout.Y_AXIS)); // Arrange components vertically
                cheesePanel.setBackground(Color.BLACK); // Set background color

                // JLabel for the cheese options
                JLabel cheeseLabel = new JLabel("\nChoose Cheese:");
                Font thirdFont = cheeseLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                cheeseLabel.setForeground(Color.WHITE); // Set text color
                cheeseLabel.setFont(thirdFont); // Set the font

                // Creates check boxes for cheese options
                JRadioButton regularButton = new JRadioButton("Regular");
                JRadioButton veganButton = new JRadioButton("Vegan cheese");
                JRadioButton noCheeseButton = new JRadioButton("None");
                noCheeseButton.setForeground(Color.WHITE);
                veganButton.setForeground(Color.WHITE);
                regularButton.setForeground(Color.WHITE);

                // Button group for cheese options
                ButtonGroup cheeseButtons = new ButtonGroup();
                cheeseButtons.add(regularButton);
                cheeseButtons.add(veganButton);
                cheeseButtons.add(noCheeseButton);

                // Adds the cheese label and check boxes to the cheese panel
                cheesePanel.add(cheeseLabel); // Add the label
                cheesePanel.add(regularButton);
                cheesePanel.add(veganButton);
                cheesePanel.add(noCheeseButton);

                //––––––––––SAUCE PANEL––––––––––//
                // Creates a panel for sauce options
                JPanel saucePanel = new JPanel();
                saucePanel.setLayout(new BoxLayout(saucePanel, BoxLayout.Y_AXIS)); // Arrange components vertically
                saucePanel.setBackground(Color.BLACK); // Set background color

                // JLabel for the crust size
                JLabel sauceLabel = new JLabel("\nSelect Sauce:");
                Font fourthFont = sauceLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                sauceLabel.setForeground(Color.WHITE); // Set text color
                sauceLabel.setFont(fourthFont); // Set the font

                // Creates check boxes for sauce options
                JRadioButton redButton = new JRadioButton("Red Sauce");
                JRadioButton pestoButton = new JRadioButton("Pesto");
                JRadioButton noSauceButton = new JRadioButton("No Sauce");
                noSauceButton.setForeground(Color.WHITE);
                pestoButton.setForeground(Color.WHITE);
                redButton.setForeground(Color.WHITE);

                // Button group for sauce options
                ButtonGroup sauceButtons = new ButtonGroup();
                sauceButtons.add(redButton);
                sauceButtons.add(pestoButton);
                sauceButtons.add(noSauceButton);

                // Adds the crust label and check boxes to the crust panel
                saucePanel.add(sauceLabel); // Add the label
                saucePanel.add(redButton);
                saucePanel.add(pestoButton);
                saucePanel.add(noSauceButton);

                //––––––––––TOPPINGS PANEL––––––––––//
                // Creates a panel for toppings options
                JPanel toppingsPanel = new JPanel();
                toppingsPanel.setLayout(new BoxLayout(toppingsPanel, BoxLayout.Y_AXIS)); // Arrange components vertically
                toppingsPanel.setBackground(Color.BLACK); // Set background color

                // Adds a label to indicate topping options
                JLabel toppingsLabel = new JLabel("Add Toppings: $1.75 sm, $3.00 lg");
                Font fifthFont = toppingsLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                toppingsLabel.setForeground(Color.WHITE); // Set text color
                toppingsLabel.setFont(fifthFont); // Set the font

                // Create a panel to hold the checkboxes for toppings
                JPanel toppingsCheckBoxPanel = new JPanel();
                toppingsCheckBoxPanel.setLayout(new BoxLayout(toppingsCheckBoxPanel, BoxLayout.Y_AXIS));
                toppingsCheckBoxPanel.setBackground(Color.BLACK);

                // Creates an array of JCheckBox for toppings
                JCheckBox[] toppingsCheckboxes = new JCheckBox[ToppingsEnum.Toppings.values().length];
                for (ToppingsEnum.Toppings topping : ToppingsEnum.Toppings.values()) {
                    JCheckBox checkbox = new JCheckBox(topping.getName());
                    checkbox.setForeground(Color.WHITE);
                    toppingsCheckboxes[topping.ordinal()] = checkbox;
                    toppingsCheckBoxPanel.add(checkbox);
                }

                // Create a scroll pane to hold the toppings panel
                JScrollPane scrollPane = new JScrollPane(toppingsCheckBoxPanel);
                scrollPane.setBackground(Color.BLACK);
                scrollPane.setPreferredSize(new Dimension(200, 100));
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                toppingsPanel.add(scrollPane, BorderLayout.CENTER);

                // Adds the topping label and drop-down menu to the panel
                toppingsPanel.add(toppingsLabel);
                toppingsPanel.add(scrollPane);

                // Adds all panels to the BYO Pizza panel
                byoComponentsPanel.add(crustPanel);
                byoComponentsPanel.add(cheesePanel);
                byoComponentsPanel.add(saucePanel);
                byoComponentsPanel.add(toppingsPanel);

                //––––––––––BYO PIZZA PANEL––––––––––//
                // Creates a panel to hold the components using (2x2) GridLayout
                JPanel byoPanel = new JPanel(new GridLayout(2, 2));
                byoPanel.setPreferredSize(new Dimension(500, 375));
                byoPanel.setBackground(Color.BLACK); // Set background color
                byoPanel.add(byoHeaderPanel, BorderLayout.NORTH);

                // Adds the BYO Pizza components panel below the header
                byoPanel.add(byoComponentsPanel, BorderLayout.CENTER);

                // Displays the menu panel in a dialog
                int choice2 = JOptionPane.showConfirmDialog(
                        Main.this,
                        byoPanel,
                        "Build-Your-Own Pizza",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (choice2 == JOptionPane.OK_OPTION) {
                    // Determines the pizza price based on user's selection
                    double totalPrice = 0.00;

                    // Size buttons price adjustments
                    if (smallButton.isSelected()) {
                        totalPrice += 13.75;
                    } else if (gfButton.isSelected()) {
                        totalPrice += 16.75;
                    } else if (largeButton.isSelected()) {
                        totalPrice += 18.75;
                    }

                    // Retrieve the selected toppings from the checkboxes
                    for (ToppingsEnum.Toppings topping : ToppingsEnum.Toppings.values()) {
                        JCheckBox checkbox = toppingsCheckboxes[topping.ordinal()];
                        if (checkbox.isSelected()) {
                            // Determine if small size or gluten-free (small) size is selected
                            boolean smallOrGF = smallButton.isSelected() || gfButton.isSelected();
                            // Get the topping price from the ToppingsEnum using the getter based on size
                            double toppingPrice = smallOrGF ? topping.getSmallPrice() : topping.getLargePrice();

                            // Add the topping price to the total price
                            totalPrice += toppingPrice;
                        }
                    }

                    // Ensures the trailing zeros are included in the price
                    DecimalFormat decimalFormat = new DecimalFormat("$0.00");
                    String formattedPrice = decimalFormat.format(totalPrice);

                    // Sets panel where price info is displayed
                    JPanel pricePanel = new JPanel();
                    pricePanel.setBackground(Color.BLACK);
                    pricePanel.setForeground(Color.WHITE);
                    pricePanel.setLayout(new GridLayout(7, 2)); // Two rows, one for each message

                    // Pizza prep message
                    JLabel prepLabel = new JLabel(customerName + ",\nyour pizza is being prepared!");
                    Font sixthFont = prepLabel.getFont().deriveFont(Font.PLAIN, 26); // Adjust the size as needed
                    prepLabel.setForeground(Color.WHITE); // Set text color
                    prepLabel.setFont(sixthFont); // Set the font
                    pricePanel.add(prepLabel);

                    // Order info message
                    JLabel infoLabel = new JLabel("\nPizza order information:");
                    Font seventhFont = infoLabel.getFont().deriveFont(Font.PLAIN, 20); // Adjust the size as needed
                    infoLabel.setForeground(Color.WHITE); // Set text color
                    infoLabel.setFont(seventhFont); // Set the font
                    pricePanel.add(infoLabel);

                    // Indicates selected size
                    JLabel sizeLabel = new JLabel("\nSelected Size:\n" + (smallButton.isSelected() ? "Small" : gfButton.isSelected() ? "Small Gluten-free" : "Large"));
                    Font eigthFont = sizeLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                    sizeLabel.setForeground(Color.WHITE); // Set text color
                    sizeLabel.setFont(eigthFont); // Set the font
                    pricePanel.add(sizeLabel);

                    // Indicates selected sauce
                    JLabel selectedSauceLabel = new JLabel("\nSelected Sauce:\n" + (redButton.isSelected() ? "Red Sauce" : pestoButton.isSelected() ? "Pesto Sauce" : "None"));
                    Font ninthFont = sizeLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                    selectedSauceLabel.setForeground(Color.WHITE); // Set text color
                    selectedSauceLabel.setFont(ninthFont); // Set the font
                    pricePanel.add(selectedSauceLabel);

                    // Indicates selected cheese
                    JLabel selectedCheeseLabel = new JLabel("\nSelected Cheese:\n" + (regularButton.isSelected() ? "Regular Cheese" : veganButton.isSelected() ? "Vegan Cheese" : "None"));
                    Font tenthFont = sizeLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                    selectedCheeseLabel.setForeground(Color.WHITE); // Set text color
                    selectedCheeseLabel.setFont(tenthFont); // Set the font
                    pricePanel.add(selectedCheeseLabel);

                    // Initializes a StringBuilder to build the text for selected toppings
                    StringBuilder selectedToppingsText = new StringBuilder();

                    // Iterates through the selected checkboxes for toppings
                    for (ToppingsEnum.Toppings topping : ToppingsEnum.Toppings.values()) {
                        JCheckBox checkbox = toppingsCheckboxes[topping.ordinal()];
                        if (checkbox.isSelected()) {
                            if (selectedToppingsText.length() > 0) {
                                // If there are already selected toppings, add a comma and space
                                selectedToppingsText.append(", ");
                            }
                            // Add the name of the selected topping to the text
                            selectedToppingsText.append(topping.getName());
                        }
                    }

                    // Creates a label to display selected toppings
                    JLabel selectedToppingsLabel = new JLabel("\nSelected Toppings:\n" + selectedToppingsText);
                    Font toppingsFont = selectedToppingsLabel.getFont().deriveFont(Font.PLAIN, 13); // Adjust the size as needed
                    selectedToppingsLabel.setForeground(Color.WHITE); // Set text color
                    selectedToppingsLabel.setFont(toppingsFont); // Set the font
                    pricePanel.add(selectedToppingsLabel);

                    // Indicates the pizza price
                    JLabel priceLabel = new JLabel("\nTotal Price:\n" + formattedPrice);
                    Font twelthFont = priceLabel.getFont().deriveFont(Font.PLAIN, 24); // Adjust the size as needed
                    priceLabel.setForeground(Color.WHITE); // Set text color
                    priceLabel.setFont(twelthFont); // Set the font
                    pricePanel.add(priceLabel);

                    // Creates the order confirmation frame
                    JFrame confirmationFrame = new JFrame("Order Confirmation");
                    confirmationFrame.setPreferredSize(new Dimension(700, 360));
                    confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    confirmationFrame.getContentPane().add(pricePanel);
                    confirmationFrame.pack();
                    confirmationFrame.setLocationRelativeTo(null);
                    confirmationFrame.setVisible(true);
                } else {
                    // Handles invalid selection
                    JOptionPane.showMessageDialog(
                            Main.this,
                            "Oops, no menu items were selected. Return to homepage to reorder!",
                            "Invalid Selection",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
