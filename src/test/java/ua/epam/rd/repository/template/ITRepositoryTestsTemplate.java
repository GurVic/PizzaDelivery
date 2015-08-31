/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.repository.template;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", 
        defaultRollback = true)
@FixMethodOrder(MethodSorters.JVM)
public class ITRepositoryTestsTemplate extends 
        AbstractTransactionalJUnit4SpringContextTests {//RepositoryTestsTemplate {
    
}
