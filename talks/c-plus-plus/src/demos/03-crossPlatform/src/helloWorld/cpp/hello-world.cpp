#include <iostream>
#include "greeting.h"

int main() {
    greeting(std::cout);
#ifdef FAREWELL
    std::cout << "Goodbye!" << std::endl;
#endif
    return 0;
}
