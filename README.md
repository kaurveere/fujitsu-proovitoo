# Delivery fee calculator
API usage:

    Run the Main.java file to start the program
    
    Provide city and vehicle type and get delivery fee based on most recent weather data in that city:
    calculate/{city}/{vehicle}

    Provide a timestamp and get delivery fee based on closest weather data to that time:
    calculate/{city}/{vehicle}/{timestamp}

    To scrape new data on demand:
    /scrape

Accepted city values: tallinn, tartu, parnu
Accepted vehicle_type values: car, scooter, bike
