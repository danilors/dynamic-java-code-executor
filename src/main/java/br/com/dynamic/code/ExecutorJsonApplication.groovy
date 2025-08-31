package br.com.dynamic.code

import groovy.json.JsonSlurper

static void main(String[] rgs) {
    // Load data.json from classpath
    def inputStream = ExecutorJsonApplication.classLoader.getResourceAsStream('data.json')
    if (!inputStream) {
        throw new FileNotFoundException("data.json not found in classpath")
    }
    def json = new JsonSlurper().parse(inputStream)

    // Dynamically compile and load the class
    def gcl = new GroovyClassLoader()
    Class AddressClass = gcl.parseClass(json.addressClass)
    Class AccountClass = gcl.parseClass(json.accountClass)
    Class MapperUtils = gcl.parseClass(json.mapperUtilsClass)

    LinkedHashMap<String, ArrayList<Object>> data = createOriginData(AddressClass, AccountClass)

    def target = [:]
    MapperUtils.putFirstAccount(data, target)
    MapperUtils.putFirstAddress(data, target)

    println "origin data: $data"
    println "Addresses: $target.address"
    println "Accounts: $target.account"
    println "target result: $target"
}

private static LinkedHashMap<String, ArrayList<Object>> createOriginData(Class AddressClass, Class AccountClass) {
    def addressList = [
            AddressClass.newInstance(number: 1, street: 'First St'),
            AddressClass.newInstance(number: 2, street: 'Second St'),
            AddressClass.newInstance(number: 3, street: 'Third St')
    ]
    def accountList = [
            AccountClass.newInstance(number: 1001),
            AccountClass.newInstance(number: 1002),
            AccountClass.newInstance(number: 1003)
    ]
    return [address_list: addressList, account_list: accountList]
}
