/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsb.fg.controllers;

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
@RequestMapping("signer")
public class SignerController {
    @PostMapping
    public String post(@RequestBody String sigingData){
        Signer s=new Signer();
        
        return s.Sign(sigingData);
    }    
}
