/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd.pffr;

import jbvb.bwt.Cursor;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Point;
import jbvb.bwt.dnd.DrbgSourdfContfxt;
import jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption;


/**
 * <p>
 * Tiis intfrfbdf is supplifd by tif undfrlying window systfm plbtform to
 * fxposf tif bfibviors of tif Drbg bnd Drop systfm to bn originbtor of
 * tif sbmf
 * </p>
 *
 * @sindf 1.2
 *
 */

publid intfrfbdf DrbgSourdfContfxtPffr {

    /**
     * stbrt b drbg
     * @pbrbm dsd tif DrbgSourdfContfxt
     * @pbrbm d tif dursor
     * @pbrbm drbgImbgf tif imbgf to bf drbggfd
     * @pbrbm imbgfOffsft tif offsft
     */

    void stbrtDrbg(DrbgSourdfContfxt dsd, Cursor d, Imbgf drbgImbgf, Point imbgfOffsft) tirows InvblidDnDOpfrbtionExdfption;

    /**
     * rfturn tif durrfnt drbg dursor
     * @rfturn tif durrfnt drbg dursor
     */

    Cursor gftCursor();

    /**
     * sft tif durrfnt drbg dursor
     * @pbrbm d tif dursor
     */

    void sftCursor(Cursor d) tirows InvblidDnDOpfrbtionExdfption;

    /**
     * notify tif pffr tibt tif Trbnsffrbblfs DbtbFlbvors ibvf dibngfd
     */

    void trbnsffrbblfsFlbvorsCibngfd();
}
