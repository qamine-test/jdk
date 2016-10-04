/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* Generbted By:JJTree&JbvbCC: Do not edit this line. Pbrser.jbvb */
pbckbge com.sun.jmx.snmp.IPAcl;

import jbvb.io.*;

@SuppressWbrnings("unchecked")  // generbted code, not worth fixing
clbss Pbrser/*@bgen(jjtree)*/implements PbrserTreeConstbnts, PbrserConstbnts {/*@bgen(jjtree)*/
  protected JJTPbrserStbte jjtree = new JJTPbrserStbte();

// A file cbn contbin severbl bcl definitions
//
  finbl public JDMSecurityDefs SecurityDefs() throws PbrseException {
                                   /*@bgen(jjtree) SecurityDefs */
  JDMSecurityDefs jjtn000 = new JDMSecurityDefs(JJTSECURITYDEFS);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse ACL:
        AclBlock();
        brebk;
      defbult:
        jj_lb1[0] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse TRAP:
        TrbpBlock();
        brebk;
      defbult:
        jj_lb1[1] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse INFORM:
        InformBlock();
        brebk;
      defbult:
        jj_lb1[2] = jj_gen;
        ;
      }
      jj_consume_token(0);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = fblse;
    {if (true) return jjtn000;}
    } cbtch (Throwbble jjte000) {
    if (jjtc000) {
      jjtree.clebrNodeScope(jjtn000);
      jjtc000 = fblse;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instbnceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instbnceof PbrseException) {
      {if (true) throw (PbrseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finblly {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return stbtement in function");
  }

  finbl public void AclBlock() throws PbrseException {
                  /*@bgen(jjtree) AclBlock */
  JDMAclBlock jjtn000 = new JDMAclBlock(JJTACLBLOCK);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(ACL);
      jj_consume_token(ASSIGN);
      jj_consume_token(LBRACE);
      lbbel_1:
      while (true) {
        AclItem();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse LBRACE:
          ;
          brebk;
        defbult:
          jj_lb1[3] = jj_gen;
          brebk lbbel_1;
        }
      }
      jj_consume_token(RBRACE);
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void AclItem() throws PbrseException {
                 /*@bgen(jjtree) AclItem */
  JDMAclItem jjtn000 = new JDMAclItem(JJTACLITEM);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(LBRACE);
      jjtn000.com = Communities();
      jjtn000.bccess = Access();
      Mbnbgers();
      jj_consume_token(RBRACE);
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public JDMCommunities Communities() throws PbrseException {
                               /*@bgen(jjtree) Communities */
  JDMCommunities jjtn000 = new JDMCommunities(JJTCOMMUNITIES);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(COMMUNITIES);
      jj_consume_token(ASSIGN);
      Community();
      lbbel_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse COMMA:
          ;
          brebk;
        defbult:
          jj_lb1[4] = jj_gen;
          brebk lbbel_2;
        }
        jj_consume_token(COMMA);
        Community();
      }
  jjtree.closeNodeScope(jjtn000, true);
  jjtc000 = fblse;
 {if (true) return jjtn000;}
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
    throw new Error("Missing return stbtement in function");
  }

  finbl public void Community() throws PbrseException {
 /*@bgen(jjtree) Community */
  JDMCommunity jjtn000 = new JDMCommunity(JJTCOMMUNITY);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(IDENTIFIER);
                 jjtree.closeNodeScope(jjtn000, true);
                 jjtc000 = fblse;
                jjtn000.communityString= t.imbge;
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public JDMAccess Access() throws PbrseException {
                     /*@bgen(jjtree) Access */
  JDMAccess jjtn000 = new JDMAccess(JJTACCESS);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(ACCESS);
      jj_consume_token(ASSIGN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse RO:
        jj_consume_token(RO);
                     jjtn000.bccess= RO;
        brebk;
      cbse RW:
        jj_consume_token(RW);
                     jjtn000.bccess= RW;
        brebk;
      defbult:
        jj_lb1[5] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
  jjtree.closeNodeScope(jjtn000, true);
  jjtc000 = fblse;
 {if (true) return jjtn000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
    throw new Error("Missing return stbtement in function");
  }

  finbl public void Mbnbgers() throws PbrseException {
                   /*@bgen(jjtree) Mbnbgers */
  JDMMbnbgers jjtn000 = new JDMMbnbgers(JJTMANAGERS);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(MANAGERS);
      jj_consume_token(ASSIGN);
      Host();
      lbbel_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse COMMA:
          ;
          brebk;
        defbult:
          jj_lb1[6] = jj_gen;
          brebk lbbel_3;
        }
        jj_consume_token(COMMA);
        Host();
      }
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void Host() throws PbrseException {
 /*@bgen(jjtree) Host */
  JDMHost jjtn000 = new JDMHost(JJTHOST);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse IDENTIFIER:
        HostNbme();
        brebk;
      defbult:
        jj_lb1[7] = jj_gen;
        if (jj_2_1(2147483647)) {
          NetMbsk();
        } else if (jj_2_2(2147483647)) {
          NetMbskV6();
        } else if (jj_2_3(2147483647)) {
          IpAddress();
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          cbse V6_ADDRESS:
            IpV6Address();
            brebk;
          cbse INTEGER_LITERAL:
            IpMbsk();
            brebk;
          defbult:
            jj_lb1[8] = jj_gen;
            jj_consume_token(-1);
            throw new PbrseException();
          }
        }
      }
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void HostNbme() throws PbrseException {
 /*@bgen(jjtree) HostNbme */
  JDMHostNbme jjtn000 = new JDMHostNbme(JJTHOSTNAME);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(IDENTIFIER);
                   jjtn000.nbme.bppend(t.imbge);
      lbbel_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse DOT:
          ;
          brebk;
        defbult:
          jj_lb1[9] = jj_gen;
          brebk lbbel_4;
        }
        jj_consume_token(DOT);
        t = jj_consume_token(IDENTIFIER);
   jjtn000.nbme.bppend( "." + t.imbge);
      }
    } finblly {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  finbl public void IpAddress() throws PbrseException {
 /*@bgen(jjtree) IpAddress */
JDMIpAddress jjtn000 = new JDMIpAddress(JJTIPADDRESS);
boolebn jjtc000 = true;
jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(INTEGER_LITERAL);
   jjtn000.bddress.bppend(t.imbge);
      lbbel_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse DOT:
          ;
          brebk;
        defbult:
          jj_lb1[10] = jj_gen;
          brebk lbbel_5;
        }
        jj_consume_token(DOT);
        t = jj_consume_token(INTEGER_LITERAL);
   jjtn000.bddress.bppend( "." + t.imbge);
      }
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void IpV6Address() throws PbrseException {
 /*@bgen(jjtree) IpV6Address */
JDMIpV6Address jjtn000 = new JDMIpV6Address(JJTIPV6ADDRESS);
boolebn jjtc000 = true;
jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(V6_ADDRESS);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = fblse;
   jjtn000.bddress.bppend(t.imbge);
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void IpMbsk() throws PbrseException {
 /*@bgen(jjtree) IpMbsk */
JDMIpMbsk jjtn000 = new JDMIpMbsk(JJTIPMASK);
boolebn jjtc000 = true;
jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(INTEGER_LITERAL);
   jjtn000.bddress.bppend(t.imbge);
      lbbel_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse MARK:
          ;
          brebk;
        defbult:
          jj_lb1[11] = jj_gen;
          brebk lbbel_6;
        }
        jj_consume_token(MARK);
        t = jj_consume_token(INTEGER_LITERAL);
   jjtn000.bddress.bppend( "." + t.imbge);
      }
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void NetMbsk() throws PbrseException {
 /*@bgen(jjtree) NetMbsk */
JDMNetMbsk jjtn000 = new JDMNetMbsk(JJTNETMASK);
boolebn jjtc000 = true;
jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(INTEGER_LITERAL);
   jjtn000.bddress.bppend(t.imbge);
      lbbel_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse DOT:
          ;
          brebk;
        defbult:
          jj_lb1[12] = jj_gen;
          brebk lbbel_7;
        }
        jj_consume_token(DOT);
        t = jj_consume_token(INTEGER_LITERAL);
   jjtn000.bddress.bppend( "." + t.imbge);
      }
      jj_consume_token(MASK);
      t = jj_consume_token(INTEGER_LITERAL);
                              jjtree.closeNodeScope(jjtn000, true);
                              jjtc000 = fblse;
                             jjtn000.mbsk = t.imbge;
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void NetMbskV6() throws PbrseException {
 /*@bgen(jjtree) NetMbskV6 */
JDMNetMbskV6 jjtn000 = new JDMNetMbskV6(JJTNETMASKV6);
boolebn jjtc000 = true;
jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(V6_ADDRESS);
   jjtn000.bddress.bppend(t.imbge);
      jj_consume_token(MASK);
      t = jj_consume_token(INTEGER_LITERAL);
                           jjtree.closeNodeScope(jjtn000, true);
                           jjtc000 = fblse;
                          jjtn000.mbsk = t.imbge;
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void TrbpBlock() throws PbrseException {
                   /*@bgen(jjtree) TrbpBlock */
  JDMTrbpBlock jjtn000 = new JDMTrbpBlock(JJTTRAPBLOCK);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(TRAP);
      jj_consume_token(ASSIGN);
      jj_consume_token(LBRACE);
      lbbel_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse LBRACE:
          ;
          brebk;
        defbult:
          jj_lb1[13] = jj_gen;
          brebk lbbel_8;
        }
        TrbpItem();
      }
      jj_consume_token(RBRACE);
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void TrbpItem() throws PbrseException {
                  /*@bgen(jjtree) TrbpItem */
  JDMTrbpItem jjtn000 = new JDMTrbpItem(JJTTRAPITEM);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(LBRACE);
      jjtn000.comm = TrbpCommunity();
      TrbpInterestedHost();
      lbbel_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse LBRACE:
          ;
          brebk;
        defbult:
          jj_lb1[14] = jj_gen;
          brebk lbbel_9;
        }
        Enterprise();
      }
      jj_consume_token(RBRACE);
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public JDMTrbpCommunity TrbpCommunity() throws PbrseException {
 /*@bgen(jjtree) TrbpCommunity */
  JDMTrbpCommunity jjtn000 = new JDMTrbpCommunity(JJTTRAPCOMMUNITY);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(TRAPCOMMUNITY);
      jj_consume_token(ASSIGN);
      t = jj_consume_token(IDENTIFIER);
                                      jjtree.closeNodeScope(jjtn000, true);
                                      jjtc000 = fblse;
                                      jjtn000.community= t.imbge; {if (true) return jjtn000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
    throw new Error("Missing return stbtement in function");
  }

  finbl public void TrbpInterestedHost() throws PbrseException {
                            /*@bgen(jjtree) TrbpInterestedHost */
  JDMTrbpInterestedHost jjtn000 = new JDMTrbpInterestedHost(JJTTRAPINTERESTEDHOST);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(HOSTS);
      jj_consume_token(ASSIGN);
      HostTrbp();
      lbbel_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse COMMA:
          ;
          brebk;
        defbult:
          jj_lb1[15] = jj_gen;
          brebk lbbel_10;
        }
        jj_consume_token(COMMA);
        HostTrbp();
      }
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void HostTrbp() throws PbrseException {
 /*@bgen(jjtree) HostTrbp */
  JDMHostTrbp jjtn000 = new JDMHostTrbp(JJTHOSTTRAP);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse IDENTIFIER:
        HostNbme();
        brebk;
      cbse INTEGER_LITERAL:
        IpAddress();
        brebk;
      cbse V6_ADDRESS:
        IpV6Address();
        brebk;
      defbult:
        jj_lb1[16] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void Enterprise() throws PbrseException {
 /*@bgen(jjtree) Enterprise */
  JDMEnterprise jjtn000 = new JDMEnterprise(JJTENTERPRISE);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(LBRACE);
      jj_consume_token(ENTERPRISE);
      jj_consume_token(ASSIGN);
      t = jj_consume_token(CSTRING);
                               jjtn000.enterprise= t.imbge;
      jj_consume_token(TRAPNUM);
      jj_consume_token(ASSIGN);
      TrbpNum();
      lbbel_11:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse COMMA:
          ;
          brebk;
        defbult:
          jj_lb1[17] = jj_gen;
          brebk lbbel_11;
        }
        jj_consume_token(COMMA);
        TrbpNum();
      }
      jj_consume_token(RBRACE);
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void TrbpNum() throws PbrseException {
 /*@bgen(jjtree) TrbpNum */
  JDMTrbpNum jjtn000 = new JDMTrbpNum(JJTTRAPNUM);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(INTEGER_LITERAL);
                       jjtn000.low= Integer.pbrseInt(t.imbge);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse RANGE:
        jj_consume_token(RANGE);
        t = jj_consume_token(INTEGER_LITERAL);
                           jjtn000.high= Integer.pbrseInt(t.imbge);
        brebk;
      defbult:
        jj_lb1[18] = jj_gen;
        ;
      }
    } finblly {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  finbl public void InformBlock() throws PbrseException {
                     /*@bgen(jjtree) InformBlock */
  JDMInformBlock jjtn000 = new JDMInformBlock(JJTINFORMBLOCK);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(INFORM);
      jj_consume_token(ASSIGN);
      jj_consume_token(LBRACE);
      lbbel_12:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse LBRACE:
          ;
          brebk;
        defbult:
          jj_lb1[19] = jj_gen;
          brebk lbbel_12;
        }
        InformItem();
      }
      jj_consume_token(RBRACE);
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void InformItem() throws PbrseException {
                    /*@bgen(jjtree) InformItem */
  JDMInformItem jjtn000 = new JDMInformItem(JJTINFORMITEM);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(LBRACE);
      jjtn000.comm = InformCommunity();
      InformInterestedHost();
      jj_consume_token(RBRACE);
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public JDMInformCommunity InformCommunity() throws PbrseException {
 /*@bgen(jjtree) InformCommunity */
  JDMInformCommunity jjtn000 = new JDMInformCommunity(JJTINFORMCOMMUNITY);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(INFORMCOMMUNITY);
      jj_consume_token(ASSIGN);
      t = jj_consume_token(IDENTIFIER);
                                        jjtree.closeNodeScope(jjtn000, true);
                                        jjtc000 = fblse;
                                        jjtn000.community= t.imbge; {if (true) return jjtn000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
    throw new Error("Missing return stbtement in function");
  }

  finbl public void InformInterestedHost() throws PbrseException {
                              /*@bgen(jjtree) InformInterestedHost */
  JDMInformInterestedHost jjtn000 = new JDMInformInterestedHost(JJTINFORMINTERESTEDHOST);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(HOSTS);
      jj_consume_token(ASSIGN);
      HostInform();
      lbbel_13:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse COMMA:
          ;
          brebk;
        defbult:
          jj_lb1[20] = jj_gen;
          brebk lbbel_13;
        }
        jj_consume_token(COMMA);
        HostInform();
      }
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl public void HostInform() throws PbrseException {
 /*@bgen(jjtree) HostInform */
  JDMHostInform jjtn000 = new JDMHostInform(JJTHOSTINFORM);
  boolebn jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse IDENTIFIER:
        HostNbme();
        brebk;
      cbse INTEGER_LITERAL:
        IpAddress();
        brebk;
      cbse V6_ADDRESS:
        IpV6Address();
        brebk;
      defbult:
        jj_lb1[21] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
    } cbtch (Throwbble jjte000) {
  if (jjtc000) {
    jjtree.clebrNodeScope(jjtn000);
    jjtc000 = fblse;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instbnceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instbnceof PbrseException) {
    {if (true) throw (PbrseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finblly {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  finbl privbte boolebn jj_2_1(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    boolebn retvbl = !jj_3_1();
    jj_sbve(0, xlb);
    return retvbl;
  }

  finbl privbte boolebn jj_2_2(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    boolebn retvbl = !jj_3_2();
    jj_sbve(1, xlb);
    return retvbl;
  }

  finbl privbte boolebn jj_2_3(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    boolebn retvbl = !jj_3_3();
    jj_sbve(2, xlb);
    return retvbl;
  }

  finbl privbte boolebn jj_3_3() {
    if (jj_scbn_token(INTEGER_LITERAL)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    if (jj_scbn_token(DOT)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    return fblse;
  }

  finbl privbte boolebn jj_3_2() {
    if (jj_scbn_token(V6_ADDRESS)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    if (jj_scbn_token(MASK)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    if (jj_scbn_token(INTEGER_LITERAL)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    return fblse;
  }

  finbl privbte boolebn jj_3_1() {
    if (jj_scbn_token(INTEGER_LITERAL)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_14()) { jj_scbnpos = xsp; brebk; }
      if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    }
    if (jj_scbn_token(MASK)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    if (jj_scbn_token(INTEGER_LITERAL)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    return fblse;
  }

  finbl privbte boolebn jj_3R_14() {
    if (jj_scbn_token(DOT)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    if (jj_scbn_token(INTEGER_LITERAL)) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) return fblse;
    return fblse;
  }

  public PbrserTokenMbnbger token_source;
  ASCII_ChbrStrebm jj_input_strebm;
  public Token token, jj_nt;
  privbte int jj_ntk;
  privbte Token jj_scbnpos, jj_lbstpos;
  privbte int jj_lb;
  public boolebn lookingAhebd = fblse;
  privbte boolebn jj_semLA;
  privbte int jj_gen;
  finbl privbte int[] jj_lb1 = new int[22];
  finbl privbte int[] jj_lb1_0 = {0x100,0x80000,0x100000,0x2000,0x0,0x60000,0x0,0x80000000,0x11000000,0x0,0x0,0x0,0x0,0x2000,0x2000,0x0,0x91000000,0x0,0x8000,0x2000,0x0,0x91000000,};
  finbl privbte int[] jj_lb1_1 = {0x0,0x0,0x0,0x0,0x10,0x0,0x10,0x0,0x0,0x20,0x20,0x40,0x20,0x0,0x0,0x10,0x0,0x10,0x0,0x0,0x10,0x0,};
  finbl privbte JJCblls[] jj_2_rtns = new JJCblls[3];
  privbte boolebn jj_rescbn = fblse;
  privbte int jj_gc = 0;

  public Pbrser(jbvb.io.InputStrebm strebm) {
    jj_input_strebm = new ASCII_ChbrStrebm(strebm, 1, 1);
    token_source = new PbrserTokenMbnbger(jj_input_strebm);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  public void ReInit(jbvb.io.InputStrebm strebm) {
    jj_input_strebm.ReInit(strebm, 1, 1);
    token_source.ReInit(jj_input_strebm);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  public Pbrser(jbvb.io.Rebder strebm) {
    jj_input_strebm = new ASCII_ChbrStrebm(strebm, 1, 1);
    token_source = new PbrserTokenMbnbger(jj_input_strebm);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  public void ReInit(jbvb.io.Rebder strebm) {
    jj_input_strebm.ReInit(strebm, 1, 1);
    token_source.ReInit(jj_input_strebm);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  public Pbrser(PbrserTokenMbnbger tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  public void ReInit(PbrserTokenMbnbger tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  finbl privbte Token jj_consume_token(int kind) throws PbrseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCblls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generbtePbrseException();
  }

  finbl privbte boolebn jj_scbn_token(int kind) {
    if (jj_scbnpos == jj_lbstpos) {
      jj_lb--;
      if (jj_scbnpos.next == null) {
        jj_lbstpos = jj_scbnpos = jj_scbnpos.next = token_source.getNextToken();
      } else {
        jj_lbstpos = jj_scbnpos = jj_scbnpos.next;
      }
    } else {
      jj_scbnpos = jj_scbnpos.next;
    }
    if (jj_rescbn) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scbnpos) { i++; tok = tok.next; }
      if (tok != null) jj_bdd_error_token(kind, i);
    }
    return (jj_scbnpos.kind != kind);
  }

  finbl public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  finbl public Token getToken(int index) {
    Token t = lookingAhebd ? jj_scbnpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  finbl privbte int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  privbte jbvb.util.Vector<int[]> jj_expentries = new jbvb.util.Vector<>();
  privbte int[] jj_expentry;
  privbte int jj_kind = -1;
  privbte int[] jj_lbsttokens = new int[100];
  privbte int jj_endpos;

  privbte void jj_bdd_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lbsttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lbsttokens[i];
      }
      boolebn exists = fblse;
      for (jbvb.util.Enumerbtion<int[]> enumv = jj_expentries.elements(); enumv.hbsMoreElements();) {
        int[] oldentry = enumv.nextElement();
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = fblse;
              brebk;
            }
          }
          if (exists) brebk;
        }
      }
      if (!exists) jj_expentries.bddElement(jj_expentry);
      if (pos != 0) jj_lbsttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  finbl public PbrseException generbtePbrseException() {
    jj_expentries.removeAllElements();
    boolebn[] lb1tokens = new boolebn[40];
    for (int i = 0; i < 40; i++) {
      lb1tokens[i] = fblse;
    }
    if (jj_kind >= 0) {
      lb1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 22; i++) {
      if (jj_lb1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_lb1_0[i] & (1<<j)) != 0) {
            lb1tokens[j] = true;
          }
          if ((jj_lb1_1[i] & (1<<j)) != 0) {
            lb1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 40; i++) {
      if (lb1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.bddElement(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescbn_token();
    jj_bdd_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.elementAt(i);
    }
    return new PbrseException(token, exptokseq, tokenImbge);
  }

  finbl public void enbble_trbcing() {
  }

  finbl public void disbble_trbcing() {
  }

  finbl privbte void jj_rescbn_token() {
    jj_rescbn = true;
    for (int i = 0; i < 3; i++) {
      JJCblls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_lb = p.brg; jj_lbstpos = jj_scbnpos = p.first;
          switch (i) {
            cbse 0: jj_3_1(); brebk;
            cbse 1: jj_3_2(); brebk;
            cbse 2: jj_3_3(); brebk;
          }
        }
        p = p.next;
      } while (p != null);
    }
    jj_rescbn = fblse;
  }

  finbl privbte void jj_sbve(int index, int xlb) {
    JJCblls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCblls(); brebk; }
      p = p.next;
    }
    p.gen = jj_gen + xlb - jj_lb; p.first = token; p.brg = xlb;
  }

  stbtic finbl clbss JJCblls {
    int gen;
    Token first;
    int brg;
    JJCblls next;
  }

}
