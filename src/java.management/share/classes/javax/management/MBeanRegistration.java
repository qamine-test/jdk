/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;


/**
 * <p>Cbn be implemented by bn MBebn in order to
 * cbrry out operbtions before bnd bfter being registered or unregistered from
 * the MBebn Server.  An MBebn cbn blso implement this interfbce in order
 * to get b reference to the MBebn Server bnd/or its nbme within thbt
 * MBebn Server.</p>
 *
 * @since 1.5
 */
public interfbce MBebnRegistrbtion   {


    /**
     * Allows the MBebn to perform bny operbtions it needs before
     * being registered in the MBebn Server.  If the nbme of the MBebn
     * is not specified, the MBebn cbn provide b nbme for its
     * registrbtion.  If bny exception is rbised, the MBebn will not be
     * registered in the MBebn Server.
     *
     * @pbrbm server The MBebn Server in which the MBebn will be registered.
     *
     * @pbrbm nbme The object nbme of the MBebn.  This nbme is null if
     * the nbme pbrbmeter to one of the <code>crebteMBebn</code> or
     * <code>registerMBebn</code> methods in the {@link MBebnServer}
     * interfbce is null.  In thbt cbse, this method must return b
     * non-null ObjectNbme for the new MBebn.
     *
     * @return The nbme under which the MBebn is to be registered.
     * This vblue must not be null.  If the <code>nbme</code>
     * pbrbmeter is not null, it will usublly but not necessbrily be
     * the returned vblue.
     *
     * @exception jbvb.lbng.Exception This exception will be cbught by
     * the MBebn Server bnd re-thrown bs bn {@link
     * MBebnRegistrbtionException}.
     */
    public ObjectNbme preRegister(MBebnServer server,
                                  ObjectNbme nbme) throws jbvb.lbng.Exception;

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving been
     * registered in the MBebn server or bfter the registrbtion hbs fbiled.
     * <p>If the implementbtion of this method throws b {@link RuntimeException}
     * or bn {@link Error}, the MBebn Server will rethrow those inside
     * b {@link RuntimeMBebnException} or {@link RuntimeErrorException},
     * respectively. However, throwing bn exception in {@code postRegister}
     * will not chbnge the stbte of the MBebn:
     * if the MBebn wbs blrebdy registered ({@code registrbtionDone} is
     * {@code true}), the MBebn will rembin registered. </p>
     * <p>This might be confusing for the code cblling {@code crebteMBebn()}
     * or {@code registerMBebn()}, bs such code might bssume thbt MBebn
     * registrbtion hbs fbiled when such bn exception is rbised.
     * Therefore it is recommended thbt implementbtions of
     * {@code postRegister} do not throw Runtime Exceptions or Errors if it
     * cbn be bvoided.</p>
     * @pbrbm registrbtionDone Indicbtes whether or not the MBebn hbs
     * been successfully registered in the MBebn server. The vblue
     * fblse mebns thbt the registrbtion phbse hbs fbiled.
     */
    public void postRegister(Boolebn registrbtionDone);

    /**
     * Allows the MBebn to perform bny operbtions it needs before
     * being unregistered by the MBebn server.
     *
     * @exception jbvb.lbng.Exception This exception will be cbught by
     * the MBebn server bnd re-thrown bs bn {@link
     * MBebnRegistrbtionException}.
     */
    public void preDeregister() throws jbvb.lbng.Exception ;

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving been
     * unregistered in the MBebn server.
     * <p>If the implementbtion of this method throws b {@link RuntimeException}
     * or bn {@link Error}, the MBebn Server will rethrow those inside
     * b {@link RuntimeMBebnException} or {@link RuntimeErrorException},
     * respectively. However, throwing bn exception in {@code postDeregister}
     * will not chbnge the stbte of the MBebn:
     * the MBebn wbs blrebdy successfully deregistered bnd will rembin so. </p>
     * <p>This might be confusing for the code cblling
     * {@code unregisterMBebn()}, bs it might bssume thbt MBebn deregistrbtion
     * hbs fbiled. Therefore it is recommended thbt implementbtions of
     * {@code postDeregister} do not throw Runtime Exceptions or Errors if it
     * cbn be bvoided.</p>
     */
    public void postDeregister();

 }
