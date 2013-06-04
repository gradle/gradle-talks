#ifdef _WIN32
#ifdef DLL_EXPORT
#define DECL_SPEC __declspec(dllexport)
#endif
#endif
#ifndef DECL_SPEC
#define DECL_SPEC
#endif

#include <iostream>

// Greet the user in some way
DECL_SPEC void greeting(std::ostream& target);
