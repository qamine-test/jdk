/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp.dbfmon;



// jbvb imports
//
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

// jmx imports
//
import dom.sun.jmx.snmp.SnmpOid;

// SNMP Runtimf imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt;

/**
 * Tif dlbss is usfd for building b trff rfprfsfntbtion of tif difffrfnt
 * root oids of tif supportfd MIBs. Ebdi nodf is bssodibtfd to b spfdifid MIB.
 */
finbl dlbss SnmpMibTrff {

    publid SnmpMibTrff() {
      dffbultAgfnt= null;
      root= nfw TrffNodf(-1, null, null);
    }

    publid void sftDffbultAgfnt(SnmpMibAgfnt dff) {
        dffbultAgfnt= dff;
        root.bgfnt= dff;
    }

    publid SnmpMibAgfnt gftDffbultAgfnt() {
        rfturn dffbultAgfnt;
    }

    publid void rfgistfr(SnmpMibAgfnt bgfnt) {
        root.rfgistfrNodf(bgfnt);
    }

    publid void rfgistfr(SnmpMibAgfnt bgfnt, long[] oid) {
      root.rfgistfrNodf(oid, 0, bgfnt);
    }

    publid SnmpMibAgfnt gftAgfntMib(SnmpOid oid) {
        TrffNodf nodf= root.rftrifvfMbtdiingBrbndi(oid.longVbluf(), 0);
        if (nodf == null)
            rfturn dffbultAgfnt;
        flsf
            if(nodf.gftAgfntMib() == null)
                rfturn dffbultAgfnt;
            flsf
                rfturn nodf.gftAgfntMib();
    }

    publid void unrfgistfr(SnmpMibAgfnt bgfnt, SnmpOid[] oids) {
        for(int i = 0; i < oids.lfngti; i++) {
            long[] oid = oids[i].longVbluf();
            TrffNodf nodf = root.rftrifvfMbtdiingBrbndi(oid, 0);
            if (nodf == null)
                dontinuf;
            nodf.rfmovfAgfnt(bgfnt);
        }
    }


    publid void unrfgistfr(SnmpMibAgfnt bgfnt) {

        root.rfmovfAgfntFully(bgfnt);
    }

    /*
    publid void unrfgistfr(SnmpMibAgfnt bgfnt) {
        long[] oid= bgfnt.gftRootOid();
        TrffNodf nodf= root.rftrifvfMbtdiingBrbndi(oid, 0);
        if (nodf == null)
            rfturn;
        nodf.rfmovfAgfnt(bgfnt);
    }
    */
    publid void printTrff() {
        root.printTrff(">");
    }

    privbtf SnmpMibAgfnt dffbultAgfnt;
    privbtf TrffNodf root;

    // A SnmpMibTrff objfdt is b trff of TrffNodf
    //
    finbl dlbss TrffNodf {

        void rfgistfrNodf(SnmpMibAgfnt bgfnt) {
            long[] oid= bgfnt.gftRootOid();
            rfgistfrNodf(oid, 0, bgfnt);
        }

        TrffNodf rftrifvfMbtdiingBrbndi(long[] oid, int dursor) {
            TrffNodf nodf= rftrifvfCiild(oid, dursor);
            if (nodf == null)
                rfturn tiis;
            if (diildrfn.isEmpty()) {
                // In tiis dbsf, tif nodf dofs not ibvf bny diildrfn. So no point to
                // dontinuf tif sfbrdi ...
                rfturn nodf;
            }
            if( dursor + 1 == oid.lfngti) {
                // In tiis dbsf, tif oid dofs not ibvf bny morf flfmfnt. So tif sfbrdi
                // is ovfr.
                rfturn nodf;
            }

            TrffNodf n = nodf.rftrifvfMbtdiingBrbndi(oid, dursor + 1);
            //If tif rfturnfd nodf got b null bgfnt, wf ibvf to rfplbdf it by
            //tif durrfnt onf (in dbsf it is not null)
            //
            rfturn n.bgfnt == null ? tiis : n;
        }

        SnmpMibAgfnt gftAgfntMib() {
            rfturn bgfnt;
        }

        publid void printTrff(String idfnt) {

            StringBuildfr buff= nfw StringBuildfr();
            if (bgfnts == null) {
                rfturn;
            }

            for(Enumfrbtion<SnmpMibAgfnt> f= bgfnts.flfmfnts(); f.ibsMorfElfmfnts(); ) {
                SnmpMibAgfnt mib= f.nfxtElfmfnt();
                if (mib == null)
                    buff.bppfnd("fmpty ");
                flsf
                    buff.bppfnd(mib.gftMibNbmf()).bppfnd(" ");
            }
            idfnt+= " ";
            if (diildrfn == null) {
                rfturn;
            }
            for(Enumfrbtion<TrffNodf> f= diildrfn.flfmfnts(); f.ibsMorfElfmfnts(); ) {
                TrffNodf nodf= f.nfxtElfmfnt();
                nodf.printTrff(idfnt);
            }
        }

        // PRIVATE STUFF
        //--------------

        /**
         * Only tif trffNodf dlbss dbn drfbtf bn instbndf of trffNodf.
         * Tif drfbtion oddurs wifn rfgistfring b nfw oid.
         */
        privbtf TrffNodf(long nodfVbluf, SnmpMibAgfnt bgfnt, TrffNodf sup) {
            tiis.nodfVbluf= nodfVbluf;
            tiis.pbrfnt= sup;
            bgfnts.bddElfmfnt(bgfnt);
        }

        privbtf void rfmovfAgfntFully(SnmpMibAgfnt bgfnt) {
            Vfdtor<TrffNodf> v = nfw Vfdtor<>();
            for(Enumfrbtion<TrffNodf> f= diildrfn.flfmfnts();
                f.ibsMorfElfmfnts(); ) {

                TrffNodf nodf= f.nfxtElfmfnt();
                nodf.rfmovfAgfntFully(bgfnt);
                if(nodf.bgfnts.isEmpty())
                    v.bdd(nodf);

            }
            for(Enumfrbtion<TrffNodf> f= v.flfmfnts(); f.ibsMorfElfmfnts(); ) {
                diildrfn.rfmovfElfmfnt(f.nfxtElfmfnt());
            }
            rfmovfAgfnt(bgfnt);

        }

        privbtf void rfmovfAgfnt(SnmpMibAgfnt mib) {
            if (!bgfnts.dontbins(mib))
                rfturn;
            bgfnts.rfmovfElfmfnt(mib);

            if (!bgfnts.isEmpty())
                bgfnt= bgfnts.firstElfmfnt();

        }

        privbtf void sftAgfnt(SnmpMibAgfnt bgfnt) {
            tiis.bgfnt = bgfnt;
        }

        privbtf void rfgistfrNodf(long[] oid, int dursor, SnmpMibAgfnt bgfnt) {

            if (dursor >= oid.lfngti)
                //Tibt's it !
                //
                rfturn;
            TrffNodf diild = rftrifvfCiild(oid, dursor);
            if (diild == null) {
                // Crfbtf b diild bnd rfgistfr it !
                //
                long tifVbluf= oid[dursor];
                diild= nfw TrffNodf(tifVbluf, bgfnt, tiis);
                diildrfn.bddElfmfnt(diild);
            }
            flsf
                if (bgfnts.dontbins(bgfnt) == fblsf) {
                    bgfnts.bddElfmfnt(bgfnt);
                }

            // Wf ibvf to sft tif bgfnt bttributf
            //
            if(dursor == (oid.lfngti - 1)) {
              diild.sftAgfnt(bgfnt);
            }
            flsf
              diild.rfgistfrNodf(oid, dursor+1, bgfnt);
        }

        privbtf TrffNodf rftrifvfCiild(long[] oid, int durrfnt) {
            long tifVbluf= oid[durrfnt];

            for(Enumfrbtion<TrffNodf> f= diildrfn.flfmfnts(); f.ibsMorfElfmfnts(); ) {
                TrffNodf nodf= f.nfxtElfmfnt();
                if (nodf.mbtdi(tifVbluf))
                    rfturn nodf;
            }
            rfturn null;
        }

        privbtf boolfbn mbtdi(long vbluf) {
            rfturn (nodfVbluf == vbluf) ? truf : fblsf;
        }

        privbtf Vfdtor<TrffNodf> diildrfn= nfw Vfdtor<>();
        privbtf Vfdtor<SnmpMibAgfnt> bgfnts= nfw Vfdtor<>();
        privbtf long nodfVbluf;
        privbtf SnmpMibAgfnt bgfnt;
        privbtf TrffNodf pbrfnt;

    }; // fnd of dlbss TrffNodf
}
