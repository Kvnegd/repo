#include <stdint.h>
#include <stdio.h>

uint32_t inputs[] = {1, 3, 5, 7, 9, 0};
extern uint32_t collatz(uint32_t n, int d);

int main() {
  uint32_t i;

  for (i = 0; inputs[i]; i++) {
    printf("%d -> %d\n", inputs[i], collatz(inputs[i], 0));
  }
}
