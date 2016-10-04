/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd.peer;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dnd.DropTbrget;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;

/**
 * <p>
 * This interfbce is exposed by the underlying window system plbtform to
 * enbble control of plbtform DnD operbtions
 * </p>
 *
 * @since 1.2
 *
 */

public interfbce DropTbrgetContextPeer {

    /**
     * updbte the peer's notion of the Tbrget's bctions
     * @pbrbm bctions the bctions
     */

    void setTbrgetActions(int bctions);

    /**
     * get the current Tbrget bctions
     * @return the current Tbrget bctions
     */

    int getTbrgetActions();

    /**
     * get the DropTbrget bssocibted with this peer
     * @return the DropTbrget bssocibted with this peer
     */

    DropTbrget getDropTbrget();

    /**
     * get the (remote) DbtbFlbvors from the peer
     * @return the (remote) DbtbFlbvors from the peer
     */

    DbtbFlbvor[] getTrbnsferDbtbFlbvors();

    /**
     * get bn input strebm to the remote dbtb
     * @return bn input strebm to the remote dbtb
     */

    Trbnsferbble getTrbnsferbble() throws InvblidDnDOperbtionException;

    /**
     * Return whether or not the DrbgSource Trbnsferbble is in the
     * sbme JVM bs the Tbrget.
     * @return if the DrbgSource Trbnsferbble is in the sbme JVM bs the Tbrget
     */

    boolebn isTrbnsferbbleJVMLocbl();

    /**
     * bccept the Drbg
     * @pbrbm drbgAction the drbg bction
     */

    void bcceptDrbg(int drbgAction);

    /**
     * reject the Drbg
     */

    void rejectDrbg();

    /**
     * bccept the Drop
     * @pbrbm dropAction the drop bction
     */

    void bcceptDrop(int dropAction);

    /**
     * reject the Drop
     */

    void rejectDrop();

    /**
     * signbl complete
     * @pbrbm success the signbl
     */

    void dropComplete(boolebn success);

}
