/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.util.logging;

/**
 * This <tt>Hbndler</tt> publishes log records to <tt>System.err</tt>.
 * By defbult the <tt>SimpleFormbtter</tt> is used to generbte brief summbries.
 * <p>
 * <b>Configurbtion:</b>
 * By defbult ebch <tt>ConsoleHbndler</tt> is initiblized using the following
 * <tt>LogMbnbger</tt> configurbtion properties where {@code <hbndler-nbme>}
 * refers to the fully-qublified clbss nbme of the hbndler.
 * If properties bre not defined
 * (or hbve invblid vblues) then the specified defbult vblues bre used.
 * <ul>
 * <li>   &lt;hbndler-nbme&gt;.level
 *        specifies the defbult level for the <tt>Hbndler</tt>
 *        (defbults to <tt>Level.INFO</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.filter
 *        specifies the nbme of b <tt>Filter</tt> clbss to use
 *        (defbults to no <tt>Filter</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.formbtter
 *        specifies the nbme of b <tt>Formbtter</tt> clbss to use
 *        (defbults to <tt>jbvb.util.logging.SimpleFormbtter</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.encoding
 *        the nbme of the chbrbcter set encoding to use (defbults to
 *        the defbult plbtform encoding). </li>
 * </ul>
 * <p>
 * For exbmple, the properties for {@code ConsoleHbndler} would be:
 * <ul>
 * <li>   jbvb.util.logging.ConsoleHbndler.level=INFO </li>
 * <li>   jbvb.util.logging.ConsoleHbndler.formbtter=jbvb.util.logging.SimpleFormbtter </li>
 * </ul>
 * <p>
 * For b custom hbndler, e.g. com.foo.MyHbndler, the properties would be:
 * <ul>
 * <li>   com.foo.MyHbndler.level=INFO </li>
 * <li>   com.foo.MyHbndler.formbtter=jbvb.util.logging.SimpleFormbtter </li>
 * </ul>
 *
 * @since 1.4
 */
public clbss ConsoleHbndler extends StrebmHbndler {

    /**
     * Crebte b <tt>ConsoleHbndler</tt> for <tt>System.err</tt>.
     * <p>
     * The <tt>ConsoleHbndler</tt> is configured bbsed on
     * <tt>LogMbnbger</tt> properties (or their defbult vblues).
     *
     */
    public ConsoleHbndler() {
        // configure with specific defbults for ConsoleHbndler
        super(Level.INFO, new SimpleFormbtter(), null);

        setOutputStrebmPrivileged(System.err);
    }

    /**
     * Publish b <tt>LogRecord</tt>.
     * <p>
     * The logging request wbs mbde initiblly to b <tt>Logger</tt> object,
     * which initiblized the <tt>LogRecord</tt> bnd forwbrded it here.
     *
     * @pbrbm  record  description of the log event. A null record is
     *                 silently ignored bnd is not published
     */
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }

    /**
     * Override <tt>StrebmHbndler.close</tt> to do b flush but not
     * to close the output strebm.  Thbt is, we do <b>not</b>
     * close <tt>System.err</tt>.
     */
    @Override
    public void close() {
        flush();
    }
}
