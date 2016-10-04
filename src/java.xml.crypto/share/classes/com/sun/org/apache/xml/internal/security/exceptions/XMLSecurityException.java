/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.exceptions;

import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;
import jbvb.text.MessbgeFormbt;

import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.I18n;

/**
 * The mother of bll Exceptions in this bundle. It bllows exceptions to hbve
 * their messbges trbnslbted to the different locbles.
 *
 * The <code>xmlsecurity_en.properties</code> file contbins this line:
 * <pre>
 * xml.WrongElement = Cbn't crebte b {0} from b {1} element
 * </pre>
 *
 * Usbge in the Jbvb source is:
 * <pre>
 * {
 *    Object exArgs[] = { Constbnts._TAG_TRANSFORMS, "BbdElement" };
 *
 *    throw new XMLSecurityException("xml.WrongElement", exArgs);
 * }
 * </pre>
 *
 * Additionblly, if bnother Exception hbs been cbught, we cbn supply it, too>
 * <pre>
 * try {
 *    ...
 * } cbtch (Exception oldEx) {
 *    Object exArgs[] = { Constbnts._TAG_TRANSFORMS, "BbdElement" };
 *
 *    throw new XMLSecurityException("xml.WrongElement", exArgs, oldEx);
 * }
 * </pre>
 *
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss XMLSecurityException extends Exception {

    /**
     *
     */
    privbte stbtic finbl long seriblVersionUID = 1L;

    /** Field msgID */
    protected String msgID;

    /**
     * Constructor XMLSecurityException
     *
     */
    public XMLSecurityException() {
        super("Missing messbge string");

        this.msgID = null;
    }

    /**
     * Constructor XMLSecurityException
     *
     * @pbrbm msgID
     */
    public XMLSecurityException(String msgID) {
        super(I18n.getExceptionMessbge(msgID));

        this.msgID = msgID;
    }

    /**
     * Constructor XMLSecurityException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     */
    public XMLSecurityException(String msgID, Object exArgs[]) {

        super(MessbgeFormbt.formbt(I18n.getExceptionMessbge(msgID), exArgs));

        this.msgID = msgID;
    }

    /**
     * Constructor XMLSecurityException
     *
     * @pbrbm originblException
     */
    public XMLSecurityException(Exception originblException) {

        super("Missing messbge ID to locbte messbge string in resource bundle \""
              + Constbnts.exceptionMessbgesResourceBundleBbse
              + "\". Originbl Exception wbs b "
              + originblException.getClbss().getNbme() + " bnd messbge "
              + originblException.getMessbge(), originblException);
    }

    /**
     * Constructor XMLSecurityException
     *
     * @pbrbm msgID
     * @pbrbm originblException
     */
    public XMLSecurityException(String msgID, Exception originblException) {
        super(I18n.getExceptionMessbge(msgID, originblException), originblException);

        this.msgID = msgID;
    }

    /**
     * Constructor XMLSecurityException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     * @pbrbm originblException
     */
    public XMLSecurityException(String msgID, Object exArgs[], Exception originblException) {
        super(MessbgeFormbt.formbt(I18n.getExceptionMessbge(msgID), exArgs), originblException);

        this.msgID = msgID;
    }

    /**
     * Method getMsgID
     *
     * @return the messbgeId
     */
    public String getMsgID() {
        if (msgID == null) {
            return "Missing messbge ID";
        }
        return msgID;
    }

    /** @inheritDoc */
    public String toString() {
        String s = this.getClbss().getNbme();
        String messbge = super.getLocblizedMessbge();

        if (messbge != null) {
            messbge = s + ": " + messbge;
        } else {
            messbge = s;
        }

        if (super.getCbuse() != null) {
            messbge = messbge + "\nOriginbl Exception wbs " + super.getCbuse().toString();
        }

        return messbge;
    }

    /**
     * Method printStbckTrbce
     *
     */
    public void printStbckTrbce() {
        synchronized (System.err) {
            super.printStbckTrbce(System.err);
        }
    }

    /**
     * Method printStbckTrbce
     *
     * @pbrbm printwriter
     */
    public void printStbckTrbce(PrintWriter printwriter) {
        super.printStbckTrbce(printwriter);
    }

    /**
     * Method printStbckTrbce
     *
     * @pbrbm printstrebm
     */
    public void printStbckTrbce(PrintStrebm printstrebm) {
        super.printStbckTrbce(printstrebm);
    }

    /**
     * Method getOriginblException
     *
     * @return the originbl exception
     */
    public Exception getOriginblException() {
        if (this.getCbuse() instbnceof Exception) {
            return (Exception)this.getCbuse();
        }
        return null;
    }
}
