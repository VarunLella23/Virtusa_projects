# FareCalc.py

# Dictionary storing fare per kilometer
fare_rates = {
    "Economy": 10,
    "Premium": 18,
    "SUV": 25
}


def calculate_fare(km, vehicle_type, hour):

    rate = fare_rates[vehicle_type]

    total_fare = km * rate

    surge = False

    if hour >= 17 and hour <= 20:
        total_fare = total_fare * 1.5
        surge = True

    return total_fare, surge


print("========== CityCab Ride Estimator ==========\n")

try:

    distance = float(input("Enter Distance (in KM): "))
    vehicle = input("Enter Vehicle Type (Economy/Premium/SUV): ")
    hour = int(input("Enter Hour of Day (0-23): "))

    if vehicle not in fare_rates:
        print("\nService Not Available")
    elif hour < 0 or hour > 23:
        print("\nInvalid Hour")
    elif distance <= 0:
        print("\nInvalid Distance")
    else:

        fare, surge_applied = calculate_fare(distance, vehicle, hour)

        print("\n========== PRICE RECEIPT ==========")
        print("Vehicle Type      :", vehicle)
        print("Distance          :", distance, "KM")
        print("Rate Per KM       : ₹", fare_rates[vehicle])

        if surge_applied:
            print("Surge Pricing     : Applied (1.5x)")
        else:
            print("Surge Pricing     : Not Applied")

        print("Final Fare        : ₹", fare)

        print("===================================")

except ValueError:

    print("\nPlease enter valid numeric input.")