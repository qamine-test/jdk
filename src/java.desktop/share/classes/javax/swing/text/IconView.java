/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.*;
import jbvbx.swing.Idon;
import jbvbx.swing.fvfnt.*;

/**
 * Idon dfdorbtor tibt implfmfnts tif vifw intfrfbdf.  Tif
 * fntirf flfmfnt is usfd to rfprfsfnt tif idon.  Tiis bdts
 * bs b gbtfwby from tif displby-only Vifw implfmfntbtions to
 * intfrbdtivf ligitwfigit idons (tibt is, it bllows idons
 * to bf fmbfddfd into tif Vifw iifrbrdiy.  Tif pbrfnt of tif idon
 * is tif dontbinfr tibt is ibndfd out by tif bssodibtfd vifw
 * fbdtory.
 *
 * @butior Timotiy Prinzing
 */
publid dlbss IdonVifw fxtfnds Vifw  {

    /**
     * Crfbtfs b nfw idon vifw tibt rfprfsfnts bn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt to drfbtf b vifw for
     */
    publid IdonVifw(Elfmfnt flfm) {
        supfr(flfm);
        AttributfSft bttr = flfm.gftAttributfs();
        d = StylfConstbnts.gftIdon(bttr);
    }

    // --- Vifw mftiods ---------------------------------------------

    /**
     * Pbints tif idon.
     * Tif rfbl pbint bfibvior oddurs nbturblly from tif bssodibtion
     * tibt tif idon ibs witi its pbrfnt dontbinfr (tif sbmf
     * dontbinfr iosting tiis vifw), so tiis simply bllows us to
     * position tif idon propfrly rflbtivf to tif vifw.  Sindf
     * tif doordinbtf systfm for tif vifw is simply tif pbrfnt
     * dontbinfrs, positioning tif diild idon is fbsy.
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @sff Vifw#pbint
     */
    publid void pbint(Grbpiids g, Sibpf b) {
        Rfdtbnglf bllod = b.gftBounds();
        d.pbintIdon(gftContbinfr(), g, bllod.x, bllod.y);
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn  tif spbn tif vifw would likf to bf rfndfrfd into
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        switdi (bxis) {
        dbsf Vifw.X_AXIS:
            rfturn d.gftIdonWidti();
        dbsf Vifw.Y_AXIS:
            rfturn d.gftIdonHfigit();
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
    }

    /**
     * Dftfrminfs tif dfsirfd blignmfnt for tiis vifw blong bn
     * bxis.  Tiis is implfmfntfd to givf tif blignmfnt to tif
     * bottom of tif idon blong tif y bxis, bnd tif dffbult
     * blong tif x bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn tif dfsirfd blignmfnt &gt;= 0.0f &bmp;&bmp; &lt;= 1.0f.  Tiis siould bf
     *   b vbluf bftwffn 0.0 bnd 1.0 wifrf 0 indidbtfs blignmfnt bt tif
     *   origin bnd 1.0 indidbtfs blignmfnt to tif full spbn
     *   bwby from tif origin.  An blignmfnt of 0.5 would bf tif
     *   dfntfr of tif vifw.
     */
    publid flobt gftAlignmfnt(int bxis) {
        switdi (bxis) {
        dbsf Vifw.Y_AXIS:
            rfturn 1;
        dffbult:
            rfturn supfr.gftAlignmfnt(bxis);
        }
    }

    /**
     * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
     * to tif doordinbtf spbdf of tif vifw mbppfd to it.
     *
     * @pbrbm pos tif position to donvfrt &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif bounding box of tif givfn position
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not
     *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff Vifw#modflToVifw
     */
    publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
        int p0 = gftStbrtOffsft();
        int p1 = gftEndOffsft();
        if ((pos >= p0) && (pos <= p1)) {
            Rfdtbnglf r = b.gftBounds();
            if (pos == p1) {
                r.x += r.widti;
            }
            r.widti = 0;
            rfturn r;
        }
        tirow nfw BbdLodbtionExdfption(pos + " not in rbngf " + p0 + "," + p1, pos);
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @pbrbm y tif Y doordinbtf &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point of vifw &gt;= 0
     * @sff Vifw#vifwToModfl
     */
    publid int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibs) {
        Rfdtbnglf bllod = (Rfdtbnglf) b;
        if (x < bllod.x + (bllod.widti / 2)) {
            bibs[0] = Position.Bibs.Forwbrd;
            rfturn gftStbrtOffsft();
        }
        bibs[0] = Position.Bibs.Bbdkwbrd;
        rfturn gftEndOffsft();
    }

    // --- mfmbfr vbribblfs ------------------------------------------------

    privbtf Idon d;
}
