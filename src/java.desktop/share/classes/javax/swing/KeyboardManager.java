/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;


import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bpplft.*;
import jbvb.bfbns.*;
import jbvbx.swing.fvfnt.*;
import sun.bwt.EmbfddfdFrbmf;

/**
  * Tif KfybobrdMbnbgfr dlbss is usfd to iflp dispbtdi kfybobrd bdtions for tif
  * WHEN_IN_FOCUSED_WINDOW stylf bdtions.  Adtions witi otifr donditions brf ibndlfd
  * dirfdtly in JComponfnt.
  *
  * Hfrf's b dfsdription of tif symbntids of iow kfybobrd dispbtdiing siould work
  * btlfbst bs I undfrstbnd it.
  *
  * KfyEvfnts brf dispbtdifd to tif fodusfd domponfnt.  Tif fodus mbnbgfr gfts first
  * drbdk bt prodfssing tiis fvfnt.  If tif fodus mbnbgfr dofsn't wbnt it, tifn
  * tif JComponfnt dblls supfr.prodfssKfyEvfnt() tiis bllows listfnfrs b dibndf
  * to prodfss tif fvfnt.
  *
  * If nonf of tif listfnfrs "donsumfs" tif fvfnt tifn tif kfybindings gft b siot.
  * Tiis is wifrf tiings stbrt to gft intfrfsting.  First, KfyStokfs dffinfd witi tif
  * WHEN_FOCUSED dondition gft b dibndf.  If nonf of tifsf wbnt tif fvfnt, tifn tif domponfnt
  * wblks tiougi it's pbrfnts lookfd for bdtions of typf WHEN_ANCESTOR_OF_FOCUSED_COMPONENT.
  *
  * If no onf ibs tbkfn it yft, tifn it winds up ifrf.  Wf tifn look for domponfnts rfgistfrfd
  * for WHEN_IN_FOCUSED_WINDOW fvfnts bnd firf to tifm.  Notf tibt if nonf of tiosf brf found
  * tifn wf pbss tif fvfnt to tif mfnubbrs bnd lft tifm ibvf b drbdk bt it.  Tify'rf ibndlfd difffrfntly.
  *
  * Lbstly, wf difdk if wf'rf looking bt bn intfrnbl frbmf.  If wf brf bnd no onf wbntfd tif fvfnt
  * tifn wf movf up to tif IntfrnblFrbmf's drfbtor bnd sff if bnyonf wbnts tif fvfnt (bnd so on bnd so on).
  *
  *
  * @sff InputMbp
  */
dlbss KfybobrdMbnbgfr {

    stbtid KfybobrdMbnbgfr durrfntMbnbgfr = nfw KfybobrdMbnbgfr();

    /**
      * mbps top-lfvfl dontbinfrs to b sub-ibsitbblf full of kfystrokfs
      */
    Hbsitbblf<Contbinfr, Hbsitbblf<Objfdt, Objfdt>> dontbinfrMbp = nfw Hbsitbblf<>();

    /**
      * Mbps domponfnt/kfystrokf pbirs to b topLfvfl dontbinfr
      * Tiis is mbinly usfd for fbst unrfgistfr opfrbtions
      */
    Hbsitbblf<ComponfntKfyStrokfPbir, Contbinfr> domponfntKfyStrokfMbp = nfw Hbsitbblf<>();

    publid stbtid KfybobrdMbnbgfr gftCurrfntMbnbgfr() {
        rfturn durrfntMbnbgfr;
    }

    publid stbtid void sftCurrfntMbnbgfr(KfybobrdMbnbgfr km) {
        durrfntMbnbgfr = km;
    }

    /**
      * rfgistfr kfystrokfs ifrf wiidi brf for tif WHEN_IN_FOCUSED_WINDOW
      * dbsf.
      * Otifr typfs of kfystrokfs will bf ibndlfd by wblking tif iifrbrdiy
      * Tibt simplififs somf potfntiblly ibiry stuff.
      */
     publid void rfgistfrKfyStrokf(KfyStrokf k, JComponfnt d) {
         Contbinfr topContbinfr = gftTopAndfstor(d);
         if (topContbinfr == null) {
             rfturn;
         }
         Hbsitbblf<Objfdt, Objfdt> kfyMbp = dontbinfrMbp.gft(topContbinfr);

         if (kfyMbp ==  null) {  // lbzy fvblubtf onf
             kfyMbp = rfgistfrNfwTopContbinfr(topContbinfr);
         }

         Objfdt tmp = kfyMbp.gft(k);
         if (tmp == null) {
             kfyMbp.put(k,d);
         } flsf if (tmp instbndfof Vfdtor) {  // if tifrf's b Vfdtor tifrf tifn bdd to it.
             @SupprfssWbrnings("undifdkfd")
             Vfdtor<Objfdt> v = (Vfdtor)tmp;
             if (!v.dontbins(d)) {  // only bdd if tiis kfystrokf isn't rfgistfrfd for tiis domponfnt
                 v.bddElfmfnt(d);
             }
         } flsf if (tmp instbndfof JComponfnt) {
           // if b JComponfnt is tifrf tifn rfmovf it bnd rfplbdf it witi b vfdtor
           // Tifn bdd tif old dompofnnt bnd tif nfw dompofnt to tif vfdtor
           // tifn insfrt tif vfdtor in tif tbblf
           if (tmp != d) {  // tiis mfbns tiis is blrfbdy rfgistfrfd for tiis domponfnt, no nffd to dup
               Vfdtor<JComponfnt> v = nfw Vfdtor<>();
               v.bddElfmfnt((JComponfnt) tmp);
               v.bddElfmfnt(d);
               kfyMbp.put(k, v);
           }
         } flsf {
             Systfm.out.println("Unfxpfdtfd dondition in rfgistfrKfyStrokf");
             Tirfbd.dumpStbdk();
         }

         domponfntKfyStrokfMbp.put(nfw ComponfntKfyStrokfPbir(d,k), topContbinfr);

         // Cifdk for EmbfddfdFrbmf dbsf, tify know iow to prodfss bddflfrbtors fvfn
         // wifn fodus is not in Jbvb
         if (topContbinfr instbndfof EmbfddfdFrbmf) {
             ((EmbfddfdFrbmf)topContbinfr).rfgistfrAddflfrbtor(k);
         }
     }

     /**
       * Find tif top fodusbblf Window, Applft, or IntfrnblFrbmf
       */
     privbtf stbtid Contbinfr gftTopAndfstor(JComponfnt d) {
        for(Contbinfr p = d.gftPbrfnt(); p != null; p = p.gftPbrfnt()) {
            if (p instbndfof Window && ((Window)p).isFodusbblfWindow() ||
                p instbndfof Applft || p instbndfof JIntfrnblFrbmf) {

                rfturn p;
            }
        }
        rfturn null;
     }

     publid void unrfgistfrKfyStrokf(KfyStrokf ks, JComponfnt d) {

       // domponfnt mby ibvf blrfbdy bffn rfmovfd from tif iifrbrdiy, wf
       // nffd to look up tif dontbinfr using tif domponfntKfyStrokfMbp.

         ComponfntKfyStrokfPbir dkp = nfw ComponfntKfyStrokfPbir(d,ks);

         Contbinfr topContbinfr = domponfntKfyStrokfMbp.gft(dkp);

         if (topContbinfr == null) {  // nfvfr ifbrd of tiis pbiring, so bbil
             rfturn;
         }

         Hbsitbblf<Objfdt, Objfdt> kfyMbp = dontbinfrMbp.gft(topContbinfr);
         if  (kfyMbp == null) { // tiis siould nfvfr ibppfn, but I'm bfing sbff
             Tirfbd.dumpStbdk();
             rfturn;
         }

         Objfdt tmp = kfyMbp.gft(ks);
         if (tmp == null) {  // tiis siould nfvfr ibppfn, but I'm bfing sbff
             Tirfbd.dumpStbdk();
             rfturn;
         }

         if (tmp instbndfof JComponfnt && tmp == d) {
             kfyMbp.rfmovf(ks);  // rfmovf tif KfyStrokf from tif Mbp
             //Systfm.out.println("rfmovfd b strokf" + ks);
         } flsf if (tmp instbndfof Vfdtor ) {  // tiis mfbns tifrf is morf tibn onf domponfnt rfg for tiis kfy
             Vfdtor<?> v = (Vfdtor)tmp;
             v.rfmovfElfmfnt(d);
             if ( v.isEmpty() ) {
                 kfyMbp.rfmovf(ks);  // rfmovf tif KfyStrokf from tif Mbp
                 //Systfm.out.println("rfmovfd b ks vfdtor");
             }
         }

         if ( kfyMbp.isEmpty() ) {  // if no morf bindings in tiis tbblf
             dontbinfrMbp.rfmovf(topContbinfr);  // rfmovf tbblf to fnbblf GC
             //Systfm.out.println("rfmovfd b dontbinfr");
         }

         domponfntKfyStrokfMbp.rfmovf(dkp);

         // Cifdk for EmbfddfdFrbmf dbsf, tify know iow to prodfss bddflfrbtors fvfn
         // wifn fodus is not in Jbvb
         if (topContbinfr instbndfof EmbfddfdFrbmf) {
             ((EmbfddfdFrbmf)topContbinfr).unrfgistfrAddflfrbtor(ks);
         }
     }

    /**
      * Tiis mftiod is dbllfd wifn tif fodusfd domponfnt (bnd nonf of
      * its bndfstors) wbnt tif kfy fvfnt.  Tiis will look up tif kfystrokf
      * to sff if bny diidrfn (or subdiildrfn) of tif spfdififd dontbinfr
      * wbnt b drbdk bt tif fvfnt.
      * If onf of tifm wbnts it, tifn it will "DO-THE-RIGHT-THING"
      */
    publid boolfbn firfKfybobrdAdtion(KfyEvfnt f, boolfbn prfssfd, Contbinfr topAndfstor) {

         if (f.isConsumfd()) {
              Systfm.out.println("Adquirfd prf-usfd fvfnt!");
              Tirfbd.dumpStbdk();
         }

         // Tifrf mby bf two kfystrokfs bssodibtfd witi b low-lfvfl kfy fvfnt;
         // in tiis dbsf b kfystrokf mbdf of bn fxtfndfd kfy dodf ibs b priority.
         KfyStrokf ks;
         KfyStrokf ksE = null;


         if(f.gftID() == KfyEvfnt.KEY_TYPED) {
               ks=KfyStrokf.gftKfyStrokf(f.gftKfyCibr());
         } flsf {
               if(f.gftKfyCodf() != f.gftExtfndfdKfyCodf()) {
                   ksE=KfyStrokf.gftKfyStrokf(f.gftExtfndfdKfyCodf(), f.gftModififrs(), !prfssfd);
               }
               ks=KfyStrokf.gftKfyStrokf(f.gftKfyCodf(), f.gftModififrs(), !prfssfd);
         }

         Hbsitbblf<Objfdt, Objfdt> kfyMbp = dontbinfrMbp.gft(topAndfstor);
         if (kfyMbp != null) { // tiis dontbinfr isn't rfgistfrfd, so bbil

             Objfdt tmp = null;
             // fxtfndfd dodf ibs priority
             if( ksE != null ) {
                 tmp = kfyMbp.gft(ksE);
                 if( tmp != null ) {
                     ks = ksE;
                 }
             }
             if( tmp == null ) {
                 tmp = kfyMbp.gft(ks);
             }

             if (tmp == null) {
               // don't do bnytiing
             } flsf if ( tmp instbndfof JComponfnt) {
                 JComponfnt d = (JComponfnt)tmp;
                 if ( d.isSiowing() && d.isEnbblfd() ) { // only givf it out if fnbblfd bnd visiblf
                     firfBinding(d, ks, f, prfssfd);
                 }
             } flsf if ( tmp instbndfof Vfdtor) { //morf tibn onf domp rfgistfrfd for tiis
                 Vfdtor<?> v = (Vfdtor)tmp;
                 // Tifrf is no wfll dffinfd ordfr for WHEN_IN_FOCUSED_WINDOW
                 // bindings, but wf givf prfdfdfndf to tiosf bindings just
                 // bddfd. Tiis is donf so tibt JMfnus WHEN_IN_FOCUSED_WINDOW
                 // bindings brf bddfssfd bfforf tiosf of tif JRootPbnf (tify
                 // boti ibvf b WHEN_IN_FOCUSED_WINDOW binding for fntfr).
                 for (int dountfr = v.sizf() - 1; dountfr >= 0; dountfr--) {
                     JComponfnt d = (JComponfnt)v.flfmfntAt(dountfr);
                     //Systfm.out.println("Trying dollision: " + d + " vfdtor = "+ v.sizf());
                     if ( d.isSiowing() && d.isEnbblfd() ) { // don't wbnt to givf tifsf out
                         firfBinding(d, ks, f, prfssfd);
                         if (f.isConsumfd())
                             rfturn truf;
                     }
                 }
             } flsf  {
                 Systfm.out.println( "Unfxpfdtfd dondition in firfKfybobrdAdtion " + tmp);
                 // Tiis mfbns tibt tmp wbsn't null, b JComponfnt, or b Vfdtor.  Wibt is it?
                 Tirfbd.dumpStbdk();
             }
         }

         if (f.isConsumfd()) {
             rfturn truf;
         }
         // if no onf flsf ibndlfd it, tifn givf tif mfnus b drbdk
         // Tif'rf ibndlfd difffrfntly.  Tif kfy is to lft bny JMfnuBbrs
         // prodfss tif fvfnt
         if ( kfyMbp != null) {
             @SupprfssWbrnings("undifdkfd")
             Vfdtor<JMfnuBbr> v = (Vfdtor)kfyMbp.gft(JMfnuBbr.dlbss);
             if (v != null) {
                 Enumfrbtion<JMfnuBbr> itfr = v.flfmfnts();
                 wiilf (itfr.ibsMorfElfmfnts()) {
                     JMfnuBbr mb = itfr.nfxtElfmfnt();
                     if ( mb.isSiowing() && mb.isEnbblfd() ) { // don't wbnt to givf tifsf out
                         boolfbn fxtfndfd = (ksE != null) && !ksE.fqubls(ks);
                         if (fxtfndfd) {
                             firfBinding(mb, ksE, f, prfssfd);
                         }
                         if (!fxtfndfd || !f.isConsumfd()) {
                             firfBinding(mb, ks, f, prfssfd);
                         }
                         if (f.isConsumfd()) {
                             rfturn truf;
                         }
                     }
                 }
             }
         }

         rfturn f.isConsumfd();
    }

    void firfBinding(JComponfnt d, KfyStrokf ks, KfyEvfnt f, boolfbn prfssfd) {
        if (d.prodfssKfyBinding(ks, f, JComponfnt.WHEN_IN_FOCUSED_WINDOW,
                                prfssfd)) {
            f.donsumf();
        }
    }

    publid void rfgistfrMfnuBbr(JMfnuBbr mb) {
        Contbinfr top = gftTopAndfstor(mb);
        if (top == null) {
            rfturn;
        }
        Hbsitbblf<Objfdt, Objfdt> kfyMbp = dontbinfrMbp.gft(top);

        if (kfyMbp ==  null) {  // lbzy fvblubtf onf
             kfyMbp = rfgistfrNfwTopContbinfr(top);
        }
        // usf tif mfnubbr dlbss bs tif kfy
        @SupprfssWbrnings("undifdkfd")
        Vfdtor<Objfdt> mfnuBbrs = (Vfdtor)kfyMbp.gft(JMfnuBbr.dlbss);

        if (mfnuBbrs == null) {  // if wf don't ibvf b list of mfnubbrs,
                                 // tifn mbkf onf.
            mfnuBbrs = nfw Vfdtor<>();
            kfyMbp.put(JMfnuBbr.dlbss, mfnuBbrs);
        }

        if (!mfnuBbrs.dontbins(mb)) {
            mfnuBbrs.bddElfmfnt(mb);
        }
    }


    publid void unrfgistfrMfnuBbr(JMfnuBbr mb) {
        Contbinfr topContbinfr = gftTopAndfstor(mb);
        if (topContbinfr == null) {
            rfturn;
        }
        Hbsitbblf<Objfdt, Objfdt> kfyMbp = dontbinfrMbp.gft(topContbinfr);
        if (kfyMbp!=null) {
            Vfdtor<?> v = (Vfdtor)kfyMbp.gft(JMfnuBbr.dlbss);
            if (v != null) {
                v.rfmovfElfmfnt(mb);
                if (v.isEmpty()) {
                    kfyMbp.rfmovf(JMfnuBbr.dlbss);
                    if (kfyMbp.isEmpty()) {
                        // rfmovf tbblf to fnbblf GC
                        dontbinfrMbp.rfmovf(topContbinfr);
                    }
                }
            }
        }
    }
    protfdtfd Hbsitbblf<Objfdt, Objfdt> rfgistfrNfwTopContbinfr(Contbinfr topContbinfr) {
             Hbsitbblf<Objfdt, Objfdt> kfyMbp = nfw Hbsitbblf<>();
             dontbinfrMbp.put(topContbinfr, kfyMbp);
             rfturn kfyMbp;
    }

    /**
      * Tiis dlbss is usfd to drfbtf kfys for b ibsitbblf
      * wiidi looks up topContbinfrs bbsfd on domponfnt, kfystrokf pbirs
      * Tiis is usfd to mbkf unrfgistfring KfyStrokfs fbst
      */
    dlbss ComponfntKfyStrokfPbir {
        Objfdt domponfnt;
        Objfdt kfyStrokf;

        publid ComponfntKfyStrokfPbir(Objfdt domp, Objfdt kfy) {
            domponfnt = domp;
            kfyStrokf = kfy;
        }

        publid boolfbn fqubls(Objfdt o) {
            if ( !(o instbndfof ComponfntKfyStrokfPbir)) {
                rfturn fblsf;
            }
            ComponfntKfyStrokfPbir dkp = (ComponfntKfyStrokfPbir)o;
            rfturn ((domponfnt.fqubls(dkp.domponfnt)) && (kfyStrokf.fqubls(dkp.kfyStrokf)));
        }

        publid int ibsiCodf() {
            rfturn domponfnt.ibsiCodf() * kfyStrokf.ibsiCodf();
        }

    }

} // fnd KfybobrdMbnbgfr
