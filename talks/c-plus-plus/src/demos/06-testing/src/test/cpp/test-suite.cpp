#include <CUnit/CUnit.h>
#include <CUnit/Basic.h>
#include <sstream>
#include "greeting.h"

void test_greeting() {
    std::ostringstream result;
    greeting(result);
    CU_ASSERT_STRING_EQUAL("Hello world!", result.str().c_str());
}

int main() {
    CU_ErrorCode result = CU_initialize_registry();
    if (result != CUE_SUCCESS) {
        return 1;
    }
    CU_pSuite suite = CU_add_suite("suite", NULL, NULL);
    CU_add_test(suite, "greeting", test_greeting);
    result = CU_basic_run_tests();
    unsigned int failures = CU_get_number_of_tests_failed();
    CU_cleanup_registry();
    return failures == 0 ? 0 : 1;
}
