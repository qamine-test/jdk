/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.BytfArrbyInputStrfbm;

import sun.misd.HfxDumpEndodfr;

/**
  * Bbsf dlbss tibt dffinfs dommon fiflds, donstbnts, bnd dfbug mftiod.
  *
  * @butior Jbgbnf Sundbr
  */
publid bbstrbdt dlbss Bfr {

    protfdtfd bytf buf[];
    protfdtfd int offsft;
    protfdtfd int bufsizf;

    protfdtfd Bfr() {
    }

    publid stbtid void dumpBER(OutputStrfbm outStrfbm, String tbg, bytf[] bytfs,
        int from, int to) {

        try {
            outStrfbm.writf('\n');
            outStrfbm.writf(tbg.gftBytfs("UTF8"));

            nfw HfxDumpEndodfr().fndodfBufffr(
                nfw BytfArrbyInputStrfbm(bytfs, from, to),
                outStrfbm);

            outStrfbm.writf('\n');
        } dbtdi (IOExdfption f) {
            try {
                outStrfbm.writf(
                    "Bfr.dumpBER(): frror fndountfrfd\n".gftBytfs("UTF8"));
            } dbtdi (IOExdfption f2) {
                // ignorf
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // somf ASN dffinfs
    //
    ////////////////////////////////////////////////////////////////////////////

    publid stbtid finbl int ASN_BOOLEAN         = 0x01;
    publid stbtid finbl int ASN_INTEGER         = 0x02;
    publid stbtid finbl int ASN_BIT_STRING      = 0x03;
    publid stbtid finbl int ASN_SIMPLE_STRING   = 0x04;
    publid stbtid finbl int ASN_OCTET_STR       = 0x04;
    publid stbtid finbl int ASN_NULL            = 0x05;
    publid stbtid finbl int ASN_OBJECT_ID       = 0x06;
    publid stbtid finbl int ASN_SEQUENCE        = 0x10;
    publid stbtid finbl int ASN_SET             = 0x11;


    publid stbtid finbl int ASN_PRIMITIVE       = 0x00;
    publid stbtid finbl int ASN_UNIVERSAL       = 0x00;
    publid stbtid finbl int ASN_CONSTRUCTOR     = 0x20;
    publid stbtid finbl int ASN_APPLICATION     = 0x40;
    publid stbtid finbl int ASN_CONTEXT         = 0x80;
    publid stbtid finbl int ASN_PRIVATE         = 0xC0;

    publid stbtid finbl int ASN_ENUMERATED      = 0x0b;

    finbl stbtid dlbss EndodfExdfption fxtfnds IOExdfption {
        privbtf stbtid finbl long sfriblVfrsionUID = -5247359637775781768L;
        EndodfExdfption(String msg) {
            supfr(msg);
        }
    }

    finbl stbtid dlbss DfdodfExdfption fxtfnds IOExdfption {
        privbtf stbtid finbl long sfriblVfrsionUID = 8735036969244425583L;
        DfdodfExdfption(String msg) {
            supfr(msg);
        }
    }
}
