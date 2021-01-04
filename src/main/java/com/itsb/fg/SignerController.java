/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsb.fg;

import biz.Signer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author miram
 */
@RestController
public class SignerController {
    @PostMapping
    public String post(@RequestBody String singingData){
        Signer s=new Signer();
        System.out.println("Incoming" +singingData);
        return s.Sign(singingData);
    }    
}
