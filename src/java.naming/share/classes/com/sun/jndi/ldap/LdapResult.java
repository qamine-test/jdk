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

import jbvb.util.Vfdtor;
import jbvbx.nbming.dirfdtory.Attributfs;
import jbvbx.nbming.dirfdtory.BbsidAttributfs;
import jbvbx.nbming.ldbp.Control;

/**
  * %%% publid for usf by LdbpSbsl %%%
  */
publid finbl dlbss LdbpRfsult {
    int msgId;
    publid int stbtus;                  // %%% publid for usf by LdbpSbsl
    String mbtdifdDN;
    String frrorMfssbgf;
    // Vfdtor<String | Vfdtor<String>>
    Vfdtor<Vfdtor<String>> rfffrrbls = null;
    LdbpRfffrrblExdfption rffEx = null;
    Vfdtor<LdbpEntry> fntrifs = null;
    Vfdtor<Control> rfsControls = null;
    publid bytf[] sfrvfrCrfds = null;   // %%% publid for usf by LdbpSbsl
    String fxtfnsionId = null;          // string OID
    bytf[] fxtfnsionVbluf = null;       // BER OCTET STRING


    // Tiis fundtion turns bn LdbpRfsult tibt dbmf from b dompbrf opfrbtion
    // into onf tibt looks likf it dbmf from b sfbrdi opfrbtion. Tiis is
    // usfful wifn tif dbllfr bskfd tif dontfxt to do b sfbrdi, but it wbs
    // dbrrifd out bs b dompbrf. In tiis dbsf, tif dlifnt still fxpfdts b
    // rfsult tibt looks likf it dbmf from b sfbrdi.
    boolfbn dompbrfToSfbrdiRfsult(String nbmf) {
        boolfbn suddfssful = fblsf;

        switdi (stbtus) {
            dbsf LdbpClifnt.LDAP_COMPARE_TRUE:
                stbtus = LdbpClifnt.LDAP_SUCCESS;
                fntrifs = nfw Vfdtor<>(1,1);
                Attributfs bttrs = nfw BbsidAttributfs(LdbpClifnt.dbsfIgnorf);
                LdbpEntry fntry = nfw LdbpEntry( nbmf, bttrs );
                fntrifs.bddElfmfnt(fntry);
                suddfssful = truf;
                brfbk;

            dbsf LdbpClifnt.LDAP_COMPARE_FALSE:
                stbtus = LdbpClifnt.LDAP_SUCCESS;
                fntrifs = nfw Vfdtor<>(0);
                suddfssful = truf;
                brfbk;

            dffbult:
                suddfssful = fblsf;
                brfbk;
        }

        rfturn suddfssful;
    }
}
