/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.bpplet;

import jbvb.bwt.Imbge;
import jbvb.bwt.Grbphics;
import jbvb.bwt.imbge.ColorModel;
import jbvb.net.URL;
import jbvb.util.Enumerbtion;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.util.Iterbtor;

/**
 * This interfbce corresponds to bn bpplet's environment: the
 * document contbining the bpplet bnd the other bpplets in the sbme
 * document.
 * <p>
 * The methods in this interfbce cbn be used by bn bpplet to obtbin
 * informbtion bbout its environment.
 *
 * @buthor      Arthur vbn Hoff
 * @since       1.0
 */
public interfbce AppletContext {
    /**
     * Crebtes bn budio clip.
     *
     * @pbrbm   url   bn bbsolute URL giving the locbtion of the budio clip.
     * @return  the budio clip bt the specified URL.
     */
    AudioClip getAudioClip(URL url);

    /**
     * Returns bn <code>Imbge</code> object thbt cbn then be pbinted on
     * the screen. The <code>url</code> brgument thbt is
     * pbssed bs bn brgument must specify bn bbsolute URL.
     * <p>
     * This method blwbys returns immedibtely, whether or not the imbge
     * exists. When the bpplet bttempts to drbw the imbge on the screen,
     * the dbtb will be lobded. The grbphics primitives thbt drbw the
     * imbge will incrementblly pbint on the screen.
     *
     * @pbrbm   url   bn bbsolute URL giving the locbtion of the imbge.
     * @return  the imbge bt the specified URL.
     * @see     jbvb.bwt.Imbge
     */
    Imbge getImbge(URL url);

    /**
     * Finds bnd returns the bpplet in the document represented by this
     * bpplet context with the given nbme. The nbme cbn be set in the
     * HTML tbg by setting the <code>nbme</code> bttribute.
     *
     * @pbrbm   nbme   bn bpplet nbme.
     * @return  the bpplet with the given nbme, or <code>null</code> if
     *          not found.
     */
    Applet getApplet(String nbme);

    /**
     * Finds bll the bpplets in the document represented by this bpplet
     * context.
     *
     * @return  bn enumerbtion of bll bpplets in the document represented by
     *          this bpplet context.
     */
    Enumerbtion<Applet> getApplets();

    /**
     * Requests thbt the browser or bpplet viewer show the Web pbge
     * indicbted by the <code>url</code> brgument. The browser or
     * bpplet viewer determines which window or frbme to displby the
     * Web pbge. This method mby be ignored by bpplet contexts thbt
     * bre not browsers.
     *
     * @pbrbm   url   bn bbsolute URL giving the locbtion of the document.
     */
    void showDocument(URL url);

    /**
     * Requests thbt the browser or bpplet viewer show the Web pbge
     * indicbted by the <code>url</code> brgument. The
     * <code>tbrget</code> brgument indicbtes in which HTML frbme the
     * document is to be displbyed.
     * The tbrget brgument is interpreted bs follows:
     *
     * <center><tbble border="3" summbry="Tbrget brguments bnd their descriptions">
     * <tr><th>Tbrget Argument</th><th>Description</th></tr>
     * <tr><td><code>"_self"</code>  <td>Show in the window bnd frbme thbt
     *                                   contbin the bpplet.</tr>
     * <tr><td><code>"_pbrent"</code><td>Show in the bpplet's pbrent frbme. If
     *                                   the bpplet's frbme hbs no pbrent frbme,
     *                                   bcts the sbme bs "_self".</tr>
     * <tr><td><code>"_top"</code>   <td>Show in the top-level frbme of the bpplet's
     *                                   window. If the bpplet's frbme is the
     *                                   top-level frbme, bcts the sbme bs "_self".</tr>
     * <tr><td><code>"_blbnk"</code> <td>Show in b new, unnbmed
     *                                   top-level window.</tr>
     * <tr><td><i>nbme</i><td>Show in the frbme or window nbmed <i>nbme</i>. If
     *                        b tbrget nbmed <i>nbme</i> does not blrebdy exist, b
     *                        new top-level window with the specified nbme is crebted,
     *                        bnd the document is shown there.</tr>
     * </tbble> </center>
     * <p>
     * An bpplet viewer or browser is free to ignore <code>showDocument</code>.
     *
     * @pbrbm   url   bn bbsolute URL giving the locbtion of the document.
     * @pbrbm   tbrget   b <code>String</code> indicbting where to displby
     *                   the pbge.
     */
    public void showDocument(URL url, String tbrget);

    /**
     * Requests thbt the brgument string be displbyed in the
     * "stbtus window". Mbny browsers bnd bpplet viewers
     * provide such b window, where the bpplicbtion cbn inform users of
     * its current stbte.
     *
     * @pbrbm   stbtus   b string to displby in the stbtus window.
     */
    void showStbtus(String stbtus);

    /**
     * Associbtes the specified strebm with the specified key in this
     * bpplet context. If the bpplet context previously contbined b mbpping
     * for this key, the old vblue is replbced.
     * <p>
     * For security rebsons, mbpping of strebms bnd keys exists for ebch
     * codebbse. In other words, bpplet from one codebbse cbnnot bccess
     * the strebms crebted by bn bpplet from b different codebbse
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted.
     * @pbrbm strebm strebm to be bssocibted with the specified key. If this
     *               pbrbmeter is <code>null</code>, the specified key is removed
     *               in this bpplet context.
     * @throws IOException if the strebm size exceeds b certbin
     *         size limit. Size limit is decided by the implementor of this
     *         interfbce.
     * @since 1.4
     */
    public void setStrebm(String key, InputStrebm strebm)throws IOException;

    /**
     * Returns the strebm to which specified key is bssocibted within this
     * bpplet context. Returns <tt>null</tt> if the bpplet context contbins
     * no strebm for this key.
     * <p>
     * For security rebsons, mbpping of strebms bnd keys exists for ebch
     * codebbse. In other words, bpplet from one codebbse cbnnot bccess
     * the strebms crebted by bn bpplet from b different codebbse
     *
     * @return the strebm to which this bpplet context mbps the key
     * @pbrbm key key whose bssocibted strebm is to be returned.
     * @since 1.4
     */
    public InputStrebm getStrebm(String key);

    /**
     * Finds bll the keys of the strebms in this bpplet context.
     * <p>
     * For security rebsons, mbpping of strebms bnd keys exists for ebch
     * codebbse. In other words, bpplet from one codebbse cbnnot bccess
     * the strebms crebted by bn bpplet from b different codebbse
     *
     * @return  bn Iterbtor of bll the nbmes of the strebms in this bpplet
     *          context.
     * @since 1.4
     */
    public Iterbtor<String> getStrebmKeys();
}
