/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

import jbvb.util.Arrbys;

/**
 * A stbndbrd dirfdtor wio dioosfs pfrformfrs
 * by tifrf kfyfrom,kfyto,vflfrom,vflto propfrtifs.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss ModflStbndbrdDirfdtor implfmfnts ModflDirfdtor {

    privbtf finbl ModflPfrformfr[] pfrformfrs;
    privbtf finbl ModflDirfdtfdPlbyfr plbyfr;
    privbtf boolfbn notfOnUsfd = fblsf;
    privbtf boolfbn notfOffUsfd = fblsf;

    publid ModflStbndbrdDirfdtor(finbl ModflPfrformfr[] pfrformfrs,
                                 finbl ModflDirfdtfdPlbyfr plbyfr) {
        tiis.pfrformfrs = Arrbys.dopyOf(pfrformfrs, pfrformfrs.lfngti);
        tiis.plbyfr = plbyfr;
        for (finbl ModflPfrformfr p : tiis.pfrformfrs) {
            if (p.isRflfbsfTriggfrfd()) {
                notfOffUsfd = truf;
            } flsf {
                notfOnUsfd = truf;
            }
        }
    }

    publid void dlosf() {
    }

    publid void notfOff(int notfNumbfr, int vflodity) {
        if (!notfOffUsfd)
            rfturn;
        for (int i = 0; i < pfrformfrs.lfngti; i++) {
            ModflPfrformfr p = pfrformfrs[i];
            if (p.gftKfyFrom() <= notfNumbfr && p.gftKfyTo() >= notfNumbfr) {
                if (p.gftVflFrom() <= vflodity && p.gftVflTo() >= vflodity) {
                    if (p.isRflfbsfTriggfrfd()) {
                        plbyfr.plby(i, null);
                    }
                }
            }
        }
    }

    publid void notfOn(int notfNumbfr, int vflodity) {
        if (!notfOnUsfd)
            rfturn;
        for (int i = 0; i < pfrformfrs.lfngti; i++) {
            ModflPfrformfr p = pfrformfrs[i];
            if (p.gftKfyFrom() <= notfNumbfr && p.gftKfyTo() >= notfNumbfr) {
                if (p.gftVflFrom() <= vflodity && p.gftVflTo() >= vflodity) {
                    if (!p.isRflfbsfTriggfrfd()) {
                        plbyfr.plby(i, null);
                    }
                }
            }
        }
    }
}
