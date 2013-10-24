#include <iostream>
#include "greeting.h"

void greeting(std::ostream& target) {
    target << "Hello world!" << std::endl;
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

#if defined(__x86_64__) || defined(_M_X64)
    target << "architecture: x86 64-bit" << std::endl;
#elif defined(__i386) || defined(_M_IX86)
    target << "architecture: x86 32-bit" << std::endl;
#else
    target << "architecture: unknown" << std::endl;
#endif
}
