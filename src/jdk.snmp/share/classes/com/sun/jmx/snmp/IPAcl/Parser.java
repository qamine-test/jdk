/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* Gfnfrbtfd By:JJTrff&JbvbCC: Do not fdit tiis linf. Pbrsfr.jbvb */
pbdkbgf dom.sun.jmx.snmp.IPAdl;

import jbvb.io.*;

@SupprfssWbrnings("undifdkfd")  // gfnfrbtfd dodf, not worti fixing
dlbss Pbrsfr/*@bgfn(jjtrff)*/implfmfnts PbrsfrTrffConstbnts, PbrsfrConstbnts {/*@bgfn(jjtrff)*/
  protfdtfd JJTPbrsfrStbtf jjtrff = nfw JJTPbrsfrStbtf();

// A filf dbn dontbin sfvfrbl bdl dffinitions
//
  finbl publid JDMSfdurityDffs SfdurityDffs() tirows PbrsfExdfption {
                                   /*@bgfn(jjtrff) SfdurityDffs */
  JDMSfdurityDffs jjtn000 = nfw JDMSfdurityDffs(JJTSECURITYDEFS);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      dbsf ACL:
        AdlBlodk();
        brfbk;
      dffbult:
        jj_lb1[0] = jj_gfn;
        ;
      }
      switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      dbsf TRAP:
        TrbpBlodk();
        brfbk;
      dffbult:
        jj_lb1[1] = jj_gfn;
        ;
      }
      switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      dbsf INFORM:
        InformBlodk();
        brfbk;
      dffbult:
        jj_lb1[2] = jj_gfn;
        ;
      }
      jj_donsumf_tokfn(0);
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
    jjtd000 = fblsf;
    {if (truf) rfturn jjtn000;}
    } dbtdi (Tirowbblf jjtf000) {
    if (jjtd000) {
      jjtrff.dlfbrNodfSdopf(jjtn000);
      jjtd000 = fblsf;
    } flsf {
      jjtrff.popNodf();
    }
    if (jjtf000 instbndfof RuntimfExdfption) {
      {if (truf) tirow (RuntimfExdfption)jjtf000;}
    }
    if (jjtf000 instbndfof PbrsfExdfption) {
      {if (truf) tirow (PbrsfExdfption)jjtf000;}
    }
    {if (truf) tirow (Error)jjtf000;}
    } finblly {
    if (jjtd000) {
      jjtrff.dlosfNodfSdopf(jjtn000, truf);
    }
    }
    tirow nfw Error("Missing rfturn stbtfmfnt in fundtion");
  }

  finbl publid void AdlBlodk() tirows PbrsfExdfption {
                  /*@bgfn(jjtrff) AdlBlodk */
  JDMAdlBlodk jjtn000 = nfw JDMAdlBlodk(JJTACLBLOCK);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(ACL);
      jj_donsumf_tokfn(ASSIGN);
      jj_donsumf_tokfn(LBRACE);
      lbbfl_1:
      wiilf (truf) {
        AdlItfm();
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf LBRACE:
          ;
          brfbk;
        dffbult:
          jj_lb1[3] = jj_gfn;
          brfbk lbbfl_1;
        }
      }
      jj_donsumf_tokfn(RBRACE);
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void AdlItfm() tirows PbrsfExdfption {
                 /*@bgfn(jjtrff) AdlItfm */
  JDMAdlItfm jjtn000 = nfw JDMAdlItfm(JJTACLITEM);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(LBRACE);
      jjtn000.dom = Communitifs();
      jjtn000.bddfss = Addfss();
      Mbnbgfrs();
      jj_donsumf_tokfn(RBRACE);
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid JDMCommunitifs Communitifs() tirows PbrsfExdfption {
                               /*@bgfn(jjtrff) Communitifs */
  JDMCommunitifs jjtn000 = nfw JDMCommunitifs(JJTCOMMUNITIES);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(COMMUNITIES);
      jj_donsumf_tokfn(ASSIGN);
      Community();
      lbbfl_2:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf COMMA:
          ;
          brfbk;
        dffbult:
          jj_lb1[4] = jj_gfn;
          brfbk lbbfl_2;
        }
        jj_donsumf_tokfn(COMMA);
        Community();
      }
  jjtrff.dlosfNodfSdopf(jjtn000, truf);
  jjtd000 = fblsf;
 {if (truf) rfturn jjtn000;}
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
    tirow nfw Error("Missing rfturn stbtfmfnt in fundtion");
  }

  finbl publid void Community() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) Community */
  JDMCommunity jjtn000 = nfw JDMCommunity(JJTCOMMUNITY);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      t = jj_donsumf_tokfn(IDENTIFIER);
                 jjtrff.dlosfNodfSdopf(jjtn000, truf);
                 jjtd000 = fblsf;
                jjtn000.dommunityString= t.imbgf;
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid JDMAddfss Addfss() tirows PbrsfExdfption {
                     /*@bgfn(jjtrff) Addfss */
  JDMAddfss jjtn000 = nfw JDMAddfss(JJTACCESS);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(ACCESS);
      jj_donsumf_tokfn(ASSIGN);
      switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      dbsf RO:
        jj_donsumf_tokfn(RO);
                     jjtn000.bddfss= RO;
        brfbk;
      dbsf RW:
        jj_donsumf_tokfn(RW);
                     jjtn000.bddfss= RW;
        brfbk;
      dffbult:
        jj_lb1[5] = jj_gfn;
        jj_donsumf_tokfn(-1);
        tirow nfw PbrsfExdfption();
      }
  jjtrff.dlosfNodfSdopf(jjtn000, truf);
  jjtd000 = fblsf;
 {if (truf) rfturn jjtn000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
    tirow nfw Error("Missing rfturn stbtfmfnt in fundtion");
  }

  finbl publid void Mbnbgfrs() tirows PbrsfExdfption {
                   /*@bgfn(jjtrff) Mbnbgfrs */
  JDMMbnbgfrs jjtn000 = nfw JDMMbnbgfrs(JJTMANAGERS);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(MANAGERS);
      jj_donsumf_tokfn(ASSIGN);
      Host();
      lbbfl_3:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf COMMA:
          ;
          brfbk;
        dffbult:
          jj_lb1[6] = jj_gfn;
          brfbk lbbfl_3;
        }
        jj_donsumf_tokfn(COMMA);
        Host();
      }
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void Host() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) Host */
  JDMHost jjtn000 = nfw JDMHost(JJTHOST);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      dbsf IDENTIFIER:
        HostNbmf();
        brfbk;
      dffbult:
        jj_lb1[7] = jj_gfn;
        if (jj_2_1(2147483647)) {
          NftMbsk();
        } flsf if (jj_2_2(2147483647)) {
          NftMbskV6();
        } flsf if (jj_2_3(2147483647)) {
          IpAddrfss();
        } flsf {
          switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          dbsf V6_ADDRESS:
            IpV6Addrfss();
            brfbk;
          dbsf INTEGER_LITERAL:
            IpMbsk();
            brfbk;
          dffbult:
            jj_lb1[8] = jj_gfn;
            jj_donsumf_tokfn(-1);
            tirow nfw PbrsfExdfption();
          }
        }
      }
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void HostNbmf() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) HostNbmf */
  JDMHostNbmf jjtn000 = nfw JDMHostNbmf(JJTHOSTNAME);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      t = jj_donsumf_tokfn(IDENTIFIER);
                   jjtn000.nbmf.bppfnd(t.imbgf);
      lbbfl_4:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf DOT:
          ;
          brfbk;
        dffbult:
          jj_lb1[9] = jj_gfn;
          brfbk lbbfl_4;
        }
        jj_donsumf_tokfn(DOT);
        t = jj_donsumf_tokfn(IDENTIFIER);
   jjtn000.nbmf.bppfnd( "." + t.imbgf);
      }
    } finblly {
    if (jjtd000) {
      jjtrff.dlosfNodfSdopf(jjtn000, truf);
    }
    }
  }

  finbl publid void IpAddrfss() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) IpAddrfss */
JDMIpAddrfss jjtn000 = nfw JDMIpAddrfss(JJTIPADDRESS);
boolfbn jjtd000 = truf;
jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      t = jj_donsumf_tokfn(INTEGER_LITERAL);
   jjtn000.bddrfss.bppfnd(t.imbgf);
      lbbfl_5:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf DOT:
          ;
          brfbk;
        dffbult:
          jj_lb1[10] = jj_gfn;
          brfbk lbbfl_5;
        }
        jj_donsumf_tokfn(DOT);
        t = jj_donsumf_tokfn(INTEGER_LITERAL);
   jjtn000.bddrfss.bppfnd( "." + t.imbgf);
      }
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void IpV6Addrfss() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) IpV6Addrfss */
JDMIpV6Addrfss jjtn000 = nfw JDMIpV6Addrfss(JJTIPV6ADDRESS);
boolfbn jjtd000 = truf;
jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      t = jj_donsumf_tokfn(V6_ADDRESS);
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
    jjtd000 = fblsf;
   jjtn000.bddrfss.bppfnd(t.imbgf);
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void IpMbsk() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) IpMbsk */
JDMIpMbsk jjtn000 = nfw JDMIpMbsk(JJTIPMASK);
boolfbn jjtd000 = truf;
jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      t = jj_donsumf_tokfn(INTEGER_LITERAL);
   jjtn000.bddrfss.bppfnd(t.imbgf);
      lbbfl_6:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf MARK:
          ;
          brfbk;
        dffbult:
          jj_lb1[11] = jj_gfn;
          brfbk lbbfl_6;
        }
        jj_donsumf_tokfn(MARK);
        t = jj_donsumf_tokfn(INTEGER_LITERAL);
   jjtn000.bddrfss.bppfnd( "." + t.imbgf);
      }
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void NftMbsk() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) NftMbsk */
JDMNftMbsk jjtn000 = nfw JDMNftMbsk(JJTNETMASK);
boolfbn jjtd000 = truf;
jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      t = jj_donsumf_tokfn(INTEGER_LITERAL);
   jjtn000.bddrfss.bppfnd(t.imbgf);
      lbbfl_7:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf DOT:
          ;
          brfbk;
        dffbult:
          jj_lb1[12] = jj_gfn;
          brfbk lbbfl_7;
        }
        jj_donsumf_tokfn(DOT);
        t = jj_donsumf_tokfn(INTEGER_LITERAL);
   jjtn000.bddrfss.bppfnd( "." + t.imbgf);
      }
      jj_donsumf_tokfn(MASK);
      t = jj_donsumf_tokfn(INTEGER_LITERAL);
                              jjtrff.dlosfNodfSdopf(jjtn000, truf);
                              jjtd000 = fblsf;
                             jjtn000.mbsk = t.imbgf;
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void NftMbskV6() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) NftMbskV6 */
JDMNftMbskV6 jjtn000 = nfw JDMNftMbskV6(JJTNETMASKV6);
boolfbn jjtd000 = truf;
jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      t = jj_donsumf_tokfn(V6_ADDRESS);
   jjtn000.bddrfss.bppfnd(t.imbgf);
      jj_donsumf_tokfn(MASK);
      t = jj_donsumf_tokfn(INTEGER_LITERAL);
                           jjtrff.dlosfNodfSdopf(jjtn000, truf);
                           jjtd000 = fblsf;
                          jjtn000.mbsk = t.imbgf;
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void TrbpBlodk() tirows PbrsfExdfption {
                   /*@bgfn(jjtrff) TrbpBlodk */
  JDMTrbpBlodk jjtn000 = nfw JDMTrbpBlodk(JJTTRAPBLOCK);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(TRAP);
      jj_donsumf_tokfn(ASSIGN);
      jj_donsumf_tokfn(LBRACE);
      lbbfl_8:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf LBRACE:
          ;
          brfbk;
        dffbult:
          jj_lb1[13] = jj_gfn;
          brfbk lbbfl_8;
        }
        TrbpItfm();
      }
      jj_donsumf_tokfn(RBRACE);
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void TrbpItfm() tirows PbrsfExdfption {
                  /*@bgfn(jjtrff) TrbpItfm */
  JDMTrbpItfm jjtn000 = nfw JDMTrbpItfm(JJTTRAPITEM);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(LBRACE);
      jjtn000.domm = TrbpCommunity();
      TrbpIntfrfstfdHost();
      lbbfl_9:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf LBRACE:
          ;
          brfbk;
        dffbult:
          jj_lb1[14] = jj_gfn;
          brfbk lbbfl_9;
        }
        Entfrprisf();
      }
      jj_donsumf_tokfn(RBRACE);
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid JDMTrbpCommunity TrbpCommunity() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) TrbpCommunity */
  JDMTrbpCommunity jjtn000 = nfw JDMTrbpCommunity(JJTTRAPCOMMUNITY);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      jj_donsumf_tokfn(TRAPCOMMUNITY);
      jj_donsumf_tokfn(ASSIGN);
      t = jj_donsumf_tokfn(IDENTIFIER);
                                      jjtrff.dlosfNodfSdopf(jjtn000, truf);
                                      jjtd000 = fblsf;
                                      jjtn000.dommunity= t.imbgf; {if (truf) rfturn jjtn000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
    tirow nfw Error("Missing rfturn stbtfmfnt in fundtion");
  }

  finbl publid void TrbpIntfrfstfdHost() tirows PbrsfExdfption {
                            /*@bgfn(jjtrff) TrbpIntfrfstfdHost */
  JDMTrbpIntfrfstfdHost jjtn000 = nfw JDMTrbpIntfrfstfdHost(JJTTRAPINTERESTEDHOST);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(HOSTS);
      jj_donsumf_tokfn(ASSIGN);
      HostTrbp();
      lbbfl_10:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf COMMA:
          ;
          brfbk;
        dffbult:
          jj_lb1[15] = jj_gfn;
          brfbk lbbfl_10;
        }
        jj_donsumf_tokfn(COMMA);
        HostTrbp();
      }
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void HostTrbp() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) HostTrbp */
  JDMHostTrbp jjtn000 = nfw JDMHostTrbp(JJTHOSTTRAP);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      dbsf IDENTIFIER:
        HostNbmf();
        brfbk;
      dbsf INTEGER_LITERAL:
        IpAddrfss();
        brfbk;
      dbsf V6_ADDRESS:
        IpV6Addrfss();
        brfbk;
      dffbult:
        jj_lb1[16] = jj_gfn;
        jj_donsumf_tokfn(-1);
        tirow nfw PbrsfExdfption();
      }
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void Entfrprisf() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) Entfrprisf */
  JDMEntfrprisf jjtn000 = nfw JDMEntfrprisf(JJTENTERPRISE);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      jj_donsumf_tokfn(LBRACE);
      jj_donsumf_tokfn(ENTERPRISE);
      jj_donsumf_tokfn(ASSIGN);
      t = jj_donsumf_tokfn(CSTRING);
                               jjtn000.fntfrprisf= t.imbgf;
      jj_donsumf_tokfn(TRAPNUM);
      jj_donsumf_tokfn(ASSIGN);
      TrbpNum();
      lbbfl_11:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf COMMA:
          ;
          brfbk;
        dffbult:
          jj_lb1[17] = jj_gfn;
          brfbk lbbfl_11;
        }
        jj_donsumf_tokfn(COMMA);
        TrbpNum();
      }
      jj_donsumf_tokfn(RBRACE);
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void TrbpNum() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) TrbpNum */
  JDMTrbpNum jjtn000 = nfw JDMTrbpNum(JJTTRAPNUM);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      t = jj_donsumf_tokfn(INTEGER_LITERAL);
                       jjtn000.low= Intfgfr.pbrsfInt(t.imbgf);
      switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      dbsf RANGE:
        jj_donsumf_tokfn(RANGE);
        t = jj_donsumf_tokfn(INTEGER_LITERAL);
                           jjtn000.iigi= Intfgfr.pbrsfInt(t.imbgf);
        brfbk;
      dffbult:
        jj_lb1[18] = jj_gfn;
        ;
      }
    } finblly {
    if (jjtd000) {
      jjtrff.dlosfNodfSdopf(jjtn000, truf);
    }
    }
  }

  finbl publid void InformBlodk() tirows PbrsfExdfption {
                     /*@bgfn(jjtrff) InformBlodk */
  JDMInformBlodk jjtn000 = nfw JDMInformBlodk(JJTINFORMBLOCK);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(INFORM);
      jj_donsumf_tokfn(ASSIGN);
      jj_donsumf_tokfn(LBRACE);
      lbbfl_12:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf LBRACE:
          ;
          brfbk;
        dffbult:
          jj_lb1[19] = jj_gfn;
          brfbk lbbfl_12;
        }
        InformItfm();
      }
      jj_donsumf_tokfn(RBRACE);
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void InformItfm() tirows PbrsfExdfption {
                    /*@bgfn(jjtrff) InformItfm */
  JDMInformItfm jjtn000 = nfw JDMInformItfm(JJTINFORMITEM);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(LBRACE);
      jjtn000.domm = InformCommunity();
      InformIntfrfstfdHost();
      jj_donsumf_tokfn(RBRACE);
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid JDMInformCommunity InformCommunity() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) InformCommunity */
  JDMInformCommunity jjtn000 = nfw JDMInformCommunity(JJTINFORMCOMMUNITY);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      jj_donsumf_tokfn(INFORMCOMMUNITY);
      jj_donsumf_tokfn(ASSIGN);
      t = jj_donsumf_tokfn(IDENTIFIER);
                                        jjtrff.dlosfNodfSdopf(jjtn000, truf);
                                        jjtd000 = fblsf;
                                        jjtn000.dommunity= t.imbgf; {if (truf) rfturn jjtn000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
    tirow nfw Error("Missing rfturn stbtfmfnt in fundtion");
  }

  finbl publid void InformIntfrfstfdHost() tirows PbrsfExdfption {
                              /*@bgfn(jjtrff) InformIntfrfstfdHost */
  JDMInformIntfrfstfdHost jjtn000 = nfw JDMInformIntfrfstfdHost(JJTINFORMINTERESTEDHOST);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);
    try {
      jj_donsumf_tokfn(HOSTS);
      jj_donsumf_tokfn(ASSIGN);
      HostInform();
      lbbfl_13:
      wiilf (truf) {
        switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        dbsf COMMA:
          ;
          brfbk;
        dffbult:
          jj_lb1[20] = jj_gfn;
          brfbk lbbfl_13;
        }
        jj_donsumf_tokfn(COMMA);
        HostInform();
      }
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl publid void HostInform() tirows PbrsfExdfption {
 /*@bgfn(jjtrff) HostInform */
  JDMHostInform jjtn000 = nfw JDMHostInform(JJTHOSTINFORM);
  boolfbn jjtd000 = truf;
  jjtrff.opfnNodfSdopf(jjtn000);Tokfn t;
    try {
      switdi ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      dbsf IDENTIFIER:
        HostNbmf();
        brfbk;
      dbsf INTEGER_LITERAL:
        IpAddrfss();
        brfbk;
      dbsf V6_ADDRESS:
        IpV6Addrfss();
        brfbk;
      dffbult:
        jj_lb1[21] = jj_gfn;
        jj_donsumf_tokfn(-1);
        tirow nfw PbrsfExdfption();
      }
    } dbtdi (Tirowbblf jjtf000) {
  if (jjtd000) {
    jjtrff.dlfbrNodfSdopf(jjtn000);
    jjtd000 = fblsf;
  } flsf {
    jjtrff.popNodf();
  }
  if (jjtf000 instbndfof RuntimfExdfption) {
    {if (truf) tirow (RuntimfExdfption)jjtf000;}
  }
  if (jjtf000 instbndfof PbrsfExdfption) {
    {if (truf) tirow (PbrsfExdfption)jjtf000;}
  }
  {if (truf) tirow (Error)jjtf000;}
    } finblly {
  if (jjtd000) {
    jjtrff.dlosfNodfSdopf(jjtn000, truf);
  }
    }
  }

  finbl privbtf boolfbn jj_2_1(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_sdbnpos = tokfn;
    boolfbn rftvbl = !jj_3_1();
    jj_sbvf(0, xlb);
    rfturn rftvbl;
  }

  finbl privbtf boolfbn jj_2_2(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_sdbnpos = tokfn;
    boolfbn rftvbl = !jj_3_2();
    jj_sbvf(1, xlb);
    rfturn rftvbl;
  }

  finbl privbtf boolfbn jj_2_3(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_sdbnpos = tokfn;
    boolfbn rftvbl = !jj_3_3();
    jj_sbvf(2, xlb);
    rfturn rftvbl;
  }

  finbl privbtf boolfbn jj_3_3() {
    if (jj_sdbn_tokfn(INTEGER_LITERAL)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    if (jj_sdbn_tokfn(DOT)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    rfturn fblsf;
  }

  finbl privbtf boolfbn jj_3_2() {
    if (jj_sdbn_tokfn(V6_ADDRESS)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    if (jj_sdbn_tokfn(MASK)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    if (jj_sdbn_tokfn(INTEGER_LITERAL)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    rfturn fblsf;
  }

  finbl privbtf boolfbn jj_3_1() {
    if (jj_sdbn_tokfn(INTEGER_LITERAL)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    Tokfn xsp;
    wiilf (truf) {
      xsp = jj_sdbnpos;
      if (jj_3R_14()) { jj_sdbnpos = xsp; brfbk; }
      if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    }
    if (jj_sdbn_tokfn(MASK)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    if (jj_sdbn_tokfn(INTEGER_LITERAL)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    rfturn fblsf;
  }

  finbl privbtf boolfbn jj_3R_14() {
    if (jj_sdbn_tokfn(DOT)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    if (jj_sdbn_tokfn(INTEGER_LITERAL)) rfturn truf;
    if (jj_lb == 0 && jj_sdbnpos == jj_lbstpos) rfturn fblsf;
    rfturn fblsf;
  }

  publid PbrsfrTokfnMbnbgfr tokfn_sourdf;
  ASCII_CibrStrfbm jj_input_strfbm;
  publid Tokfn tokfn, jj_nt;
  privbtf int jj_ntk;
  privbtf Tokfn jj_sdbnpos, jj_lbstpos;
  privbtf int jj_lb;
  publid boolfbn lookingAifbd = fblsf;
  privbtf boolfbn jj_sfmLA;
  privbtf int jj_gfn;
  finbl privbtf int[] jj_lb1 = nfw int[22];
  finbl privbtf int[] jj_lb1_0 = {0x100,0x80000,0x100000,0x2000,0x0,0x60000,0x0,0x80000000,0x11000000,0x0,0x0,0x0,0x0,0x2000,0x2000,0x0,0x91000000,0x0,0x8000,0x2000,0x0,0x91000000,};
  finbl privbtf int[] jj_lb1_1 = {0x0,0x0,0x0,0x0,0x10,0x0,0x10,0x0,0x0,0x20,0x20,0x40,0x20,0x0,0x0,0x10,0x0,0x10,0x0,0x0,0x10,0x0,};
  finbl privbtf JJCblls[] jj_2_rtns = nfw JJCblls[3];
  privbtf boolfbn jj_rfsdbn = fblsf;
  privbtf int jj_gd = 0;

  publid Pbrsfr(jbvb.io.InputStrfbm strfbm) {
    jj_input_strfbm = nfw ASCII_CibrStrfbm(strfbm, 1, 1);
    tokfn_sourdf = nfw PbrsfrTokfnMbnbgfr(jj_input_strfbm);
    tokfn = nfw Tokfn();
    jj_ntk = -1;
    jj_gfn = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.lfngti; i++) jj_2_rtns[i] = nfw JJCblls();
  }

  publid void RfInit(jbvb.io.InputStrfbm strfbm) {
    jj_input_strfbm.RfInit(strfbm, 1, 1);
    tokfn_sourdf.RfInit(jj_input_strfbm);
    tokfn = nfw Tokfn();
    jj_ntk = -1;
    jjtrff.rfsft();
    jj_gfn = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.lfngti; i++) jj_2_rtns[i] = nfw JJCblls();
  }

  publid Pbrsfr(jbvb.io.Rfbdfr strfbm) {
    jj_input_strfbm = nfw ASCII_CibrStrfbm(strfbm, 1, 1);
    tokfn_sourdf = nfw PbrsfrTokfnMbnbgfr(jj_input_strfbm);
    tokfn = nfw Tokfn();
    jj_ntk = -1;
    jj_gfn = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.lfngti; i++) jj_2_rtns[i] = nfw JJCblls();
  }

  publid void RfInit(jbvb.io.Rfbdfr strfbm) {
    jj_input_strfbm.RfInit(strfbm, 1, 1);
    tokfn_sourdf.RfInit(jj_input_strfbm);
    tokfn = nfw Tokfn();
    jj_ntk = -1;
    jjtrff.rfsft();
    jj_gfn = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.lfngti; i++) jj_2_rtns[i] = nfw JJCblls();
  }

  publid Pbrsfr(PbrsfrTokfnMbnbgfr tm) {
    tokfn_sourdf = tm;
    tokfn = nfw Tokfn();
    jj_ntk = -1;
    jj_gfn = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.lfngti; i++) jj_2_rtns[i] = nfw JJCblls();
  }

  publid void RfInit(PbrsfrTokfnMbnbgfr tm) {
    tokfn_sourdf = tm;
    tokfn = nfw Tokfn();
    jj_ntk = -1;
    jjtrff.rfsft();
    jj_gfn = 0;
    for (int i = 0; i < 22; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.lfngti; i++) jj_2_rtns[i] = nfw JJCblls();
  }

  finbl privbtf Tokfn jj_donsumf_tokfn(int kind) tirows PbrsfExdfption {
    Tokfn oldTokfn;
    if ((oldTokfn = tokfn).nfxt != null) tokfn = tokfn.nfxt;
    flsf tokfn = tokfn.nfxt = tokfn_sourdf.gftNfxtTokfn();
    jj_ntk = -1;
    if (tokfn.kind == kind) {
      jj_gfn++;
      if (++jj_gd > 100) {
        jj_gd = 0;
        for (int i = 0; i < jj_2_rtns.lfngti; i++) {
          JJCblls d = jj_2_rtns[i];
          wiilf (d != null) {
            if (d.gfn < jj_gfn) d.first = null;
            d = d.nfxt;
          }
        }
      }
      rfturn tokfn;
    }
    tokfn = oldTokfn;
    jj_kind = kind;
    tirow gfnfrbtfPbrsfExdfption();
  }

  finbl privbtf boolfbn jj_sdbn_tokfn(int kind) {
    if (jj_sdbnpos == jj_lbstpos) {
      jj_lb--;
      if (jj_sdbnpos.nfxt == null) {
        jj_lbstpos = jj_sdbnpos = jj_sdbnpos.nfxt = tokfn_sourdf.gftNfxtTokfn();
      } flsf {
        jj_lbstpos = jj_sdbnpos = jj_sdbnpos.nfxt;
      }
    } flsf {
      jj_sdbnpos = jj_sdbnpos.nfxt;
    }
    if (jj_rfsdbn) {
      int i = 0; Tokfn tok = tokfn;
      wiilf (tok != null && tok != jj_sdbnpos) { i++; tok = tok.nfxt; }
      if (tok != null) jj_bdd_frror_tokfn(kind, i);
    }
    rfturn (jj_sdbnpos.kind != kind);
  }

  finbl publid Tokfn gftNfxtTokfn() {
    if (tokfn.nfxt != null) tokfn = tokfn.nfxt;
    flsf tokfn = tokfn.nfxt = tokfn_sourdf.gftNfxtTokfn();
    jj_ntk = -1;
    jj_gfn++;
    rfturn tokfn;
  }

  finbl publid Tokfn gftTokfn(int indfx) {
    Tokfn t = lookingAifbd ? jj_sdbnpos : tokfn;
    for (int i = 0; i < indfx; i++) {
      if (t.nfxt != null) t = t.nfxt;
      flsf t = t.nfxt = tokfn_sourdf.gftNfxtTokfn();
    }
    rfturn t;
  }

  finbl privbtf int jj_ntk() {
    if ((jj_nt=tokfn.nfxt) == null)
      rfturn (jj_ntk = (tokfn.nfxt=tokfn_sourdf.gftNfxtTokfn()).kind);
    flsf
      rfturn (jj_ntk = jj_nt.kind);
  }

  privbtf jbvb.util.Vfdtor<int[]> jj_fxpfntrifs = nfw jbvb.util.Vfdtor<>();
  privbtf int[] jj_fxpfntry;
  privbtf int jj_kind = -1;
  privbtf int[] jj_lbsttokfns = nfw int[100];
  privbtf int jj_fndpos;

  privbtf void jj_bdd_frror_tokfn(int kind, int pos) {
    if (pos >= 100) rfturn;
    if (pos == jj_fndpos + 1) {
      jj_lbsttokfns[jj_fndpos++] = kind;
    } flsf if (jj_fndpos != 0) {
      jj_fxpfntry = nfw int[jj_fndpos];
      for (int i = 0; i < jj_fndpos; i++) {
        jj_fxpfntry[i] = jj_lbsttokfns[i];
      }
      boolfbn fxists = fblsf;
      for (jbvb.util.Enumfrbtion<int[]> fnumv = jj_fxpfntrifs.flfmfnts(); fnumv.ibsMorfElfmfnts();) {
        int[] oldfntry = fnumv.nfxtElfmfnt();
        if (oldfntry.lfngti == jj_fxpfntry.lfngti) {
          fxists = truf;
          for (int i = 0; i < jj_fxpfntry.lfngti; i++) {
            if (oldfntry[i] != jj_fxpfntry[i]) {
              fxists = fblsf;
              brfbk;
            }
          }
          if (fxists) brfbk;
        }
      }
      if (!fxists) jj_fxpfntrifs.bddElfmfnt(jj_fxpfntry);
      if (pos != 0) jj_lbsttokfns[(jj_fndpos = pos) - 1] = kind;
    }
  }

  finbl publid PbrsfExdfption gfnfrbtfPbrsfExdfption() {
    jj_fxpfntrifs.rfmovfAllElfmfnts();
    boolfbn[] lb1tokfns = nfw boolfbn[40];
    for (int i = 0; i < 40; i++) {
      lb1tokfns[i] = fblsf;
    }
    if (jj_kind >= 0) {
      lb1tokfns[jj_kind] = truf;
      jj_kind = -1;
    }
    for (int i = 0; i < 22; i++) {
      if (jj_lb1[i] == jj_gfn) {
        for (int j = 0; j < 32; j++) {
          if ((jj_lb1_0[i] & (1<<j)) != 0) {
            lb1tokfns[j] = truf;
          }
          if ((jj_lb1_1[i] & (1<<j)) != 0) {
            lb1tokfns[32+j] = truf;
          }
        }
      }
    }
    for (int i = 0; i < 40; i++) {
      if (lb1tokfns[i]) {
        jj_fxpfntry = nfw int[1];
        jj_fxpfntry[0] = i;
        jj_fxpfntrifs.bddElfmfnt(jj_fxpfntry);
      }
    }
    jj_fndpos = 0;
    jj_rfsdbn_tokfn();
    jj_bdd_frror_tokfn(0, 0);
    int[][] fxptoksfq = nfw int[jj_fxpfntrifs.sizf()][];
    for (int i = 0; i < jj_fxpfntrifs.sizf(); i++) {
      fxptoksfq[i] = jj_fxpfntrifs.flfmfntAt(i);
    }
    rfturn nfw PbrsfExdfption(tokfn, fxptoksfq, tokfnImbgf);
  }

  finbl publid void fnbblf_trbding() {
  }

  finbl publid void disbblf_trbding() {
  }

  finbl privbtf void jj_rfsdbn_tokfn() {
    jj_rfsdbn = truf;
    for (int i = 0; i < 3; i++) {
      JJCblls p = jj_2_rtns[i];
      do {
        if (p.gfn > jj_gfn) {
          jj_lb = p.brg; jj_lbstpos = jj_sdbnpos = p.first;
          switdi (i) {
            dbsf 0: jj_3_1(); brfbk;
            dbsf 1: jj_3_2(); brfbk;
            dbsf 2: jj_3_3(); brfbk;
          }
        }
        p = p.nfxt;
      } wiilf (p != null);
    }
    jj_rfsdbn = fblsf;
  }

  finbl privbtf void jj_sbvf(int indfx, int xlb) {
    JJCblls p = jj_2_rtns[indfx];
    wiilf (p.gfn > jj_gfn) {
      if (p.nfxt == null) { p = p.nfxt = nfw JJCblls(); brfbk; }
      p = p.nfxt;
    }
    p.gfn = jj_gfn + xlb - jj_lb; p.first = tokfn; p.brg = xlb;
  }

  stbtid finbl dlbss JJCblls {
    int gfn;
    Tokfn first;
    int brg;
    JJCblls nfxt;
  }

}
