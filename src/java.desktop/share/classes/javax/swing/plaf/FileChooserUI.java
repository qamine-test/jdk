/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf;

import jbvbx.swing.*;
import jbvbx.swing.filfdioosfr.FilfFiltfr;
import jbvbx.swing.filfdioosfr.FilfVifw;
import jbvb.io.Filf;

/**
 * Pluggbblf look bnd fffl intfrfbdf for <dodf>JFilfCioosfr</dodf>.
 *
 * @butior Jfff Dinkins
 */

publid bbstrbdt dlbss FilfCioosfrUI fxtfnds ComponfntUI
{
    publid bbstrbdt FilfFiltfr gftAddfptAllFilfFiltfr(JFilfCioosfr fd);
    publid bbstrbdt FilfVifw gftFilfVifw(JFilfCioosfr fd);

    publid bbstrbdt String gftApprovfButtonTfxt(JFilfCioosfr fd);
    publid bbstrbdt String gftDiblogTitlf(JFilfCioosfr fd);

    publid bbstrbdt void rfsdbnCurrfntDirfdtory(JFilfCioosfr fd);
    publid bbstrbdt void fnsurfFilfIsVisiblf(JFilfCioosfr fd, Filf f);

    /**
     * Rfturns dffbult button for durrfnt <dodf>LookAndFffl</dodf>.
     * <dodf>JFilfCioosfr</dodf> will usf tiis button bs dffbult button
     * for diblog windows.
     *
     * @sindf 1.7
     */
    publid JButton gftDffbultButton(JFilfCioosfr fd) {
        rfturn null;
    }
}
