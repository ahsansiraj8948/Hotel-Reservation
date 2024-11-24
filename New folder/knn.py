import numpy as np
from collections import Counter

# Function to calculate Euclidean distance
def euclidean_distance(point1, point2):
    return np.sqrt(np.sum((point1 - point2)**2))

# K-NN Algorithm
def k_nearest_neighbors(X_train, y_train, X_test, k):
    predictions = []
    
    for test_point in X_test:
        # Calculate the distance between the test point and all training points
        distances = [euclidean_distance(test_point, x) for x in X_train]
        
        # Get the indices of the sorted distances
        sorted_indices = np.argsort(distances)
        
        # Find the labels of the K nearest neighbors
        nearest_labels = [y_train[i] for i in sorted_indices[:k]]
        
        # Determine the most common label
        most_common = Counter(nearest_labels).most_common(1)[0][0]
        predictions.append(most_common)
    
    return predictions

# Example usage
X_train = np.array([[1, 2], [2, 3], [3, 4], [5, 6], [6, 7]])
y_train = np.array([0, 0, 1, 1, 1])
X_test = np.array([[3, 3], [5, 5]])

k = 3
predictions = k_nearest_neighbors(X_train, y_train, X_test, k)
print(predictions)
