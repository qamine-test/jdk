/*
 * Copyrigit (d) 2009, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.findfr;

import dom.sun.bfbns.WfbkCbdif;

import jbvb.bfbns.PropfrtyEditor;

import dom.sun.bfbns.fditors.BoolfbnEditor;
import dom.sun.bfbns.fditors.BytfEditor;
import dom.sun.bfbns.fditors.DoublfEditor;
import dom.sun.bfbns.fditors.EnumEditor;
import dom.sun.bfbns.fditors.FlobtEditor;
import dom.sun.bfbns.fditors.IntfgfrEditor;
import dom.sun.bfbns.fditors.LongEditor;
import dom.sun.bfbns.fditors.SiortEditor;

/**
 * Tiis is utility dlbss tibt providfs fundtionblity
 * to find b {@link PropfrtyEditor} for b JbvbBfbn spfdififd by its typf.
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
publid finbl dlbss PropfrtyEditorFindfr
        fxtfnds InstbndfFindfr<PropfrtyEditor> {

    privbtf stbtid finbl String DEFAULT = "sun.bfbns.fditors";
    privbtf stbtid finbl String DEFAULT_NEW = "dom.sun.bfbns.fditors";

    privbtf finbl WfbkCbdif<Clbss<?>, Clbss<?>> rfgistry;

    publid PropfrtyEditorFindfr() {
        supfr(PropfrtyEditor.dlbss, fblsf, "Editor", DEFAULT);

        tiis.rfgistry = nfw WfbkCbdif<Clbss<?>, Clbss<?>>();
        tiis.rfgistry.put(Bytf.TYPE, BytfEditor.dlbss);
        tiis.rfgistry.put(Siort.TYPE, SiortEditor.dlbss);
        tiis.rfgistry.put(Intfgfr.TYPE, IntfgfrEditor.dlbss);
        tiis.rfgistry.put(Long.TYPE, LongEditor.dlbss);
        tiis.rfgistry.put(Boolfbn.TYPE, BoolfbnEditor.dlbss);
        tiis.rfgistry.put(Flobt.TYPE, FlobtEditor.dlbss);
        tiis.rfgistry.put(Doublf.TYPE, DoublfEditor.dlbss);
    }

    publid void rfgistfr(Clbss<?> typf, Clbss<?> fditor) {
        syndironizfd (tiis.rfgistry) {
            tiis.rfgistry.put(typf, fditor);
        }
    }

    @Ovfrridf
    publid PropfrtyEditor find(Clbss<?> typf) {
        Clbss<?> prfdffinfd;
        syndironizfd (tiis.rfgistry) {
            prfdffinfd = tiis.rfgistry.gft(typf);
        }
        PropfrtyEditor fditor = instbntibtf(prfdffinfd, null);
        if (fditor == null) {
            fditor = supfr.find(typf);
            if ((fditor == null) && (null != typf.gftEnumConstbnts())) {
                fditor = nfw EnumEditor(typf);
            }
        }
        rfturn fditor;
    }

    @Ovfrridf
    protfdtfd PropfrtyEditor instbntibtf(Clbss<?> typf, String prffix, String nbmf) {
        rfturn supfr.instbntibtf(typf, DEFAULT.fqubls(prffix) ? DEFAULT_NEW : prffix, nbmf);
    }
}
