checkstyle:
	./gradlew checkstyleTest
	./gradlew checkstyleMain

test:
	./gradlew test

.PHONY: build
build: checkstyle
	./gradlew build
	./gradlew test

test-installDist:
	./gradlew installDist
	build/install/app/bin/app -h