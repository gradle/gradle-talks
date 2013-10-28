#include <iostream>
#include "greeting.h"

void greeting(std::ostream& target) {
    target << "Hello world!" << std::endl;
#if defined(__APPLE__) && defined(__MACH__)
    target << "operating system: OS X" << std::endl;
#elif defined(_WIN32)
    target << "operating system: Windows" << std::endl;
#endif

#if defined(__clang__)
    target << "compiler: Clang" << std::endl;
#elif defined(__GNUC__) && defined(__MINGW32__)
    target << "compiler: MinGW" << std::endl;
#elif defined(__GNUC__) && defined(__CYGWIN__)
    target << "compiler: GCC Cygwin" << std::endl;
#elif defined(__GNUC__)
    target << "compiler: GCC" << std::endl;
#elif defined(_MSC_VER)
    target << "compiler: Visual C++" << std::endl;
#else
    target << "compiler: unknown" << std::endl;
#endif
}
